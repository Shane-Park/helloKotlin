package com.example.concurrency.service

import com.example.concurrency.domain.Stock
import com.example.concurrency.repository.StockRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
internal class StockServiceTest {

    @Autowired
    private lateinit var stockService: StockService

    @Autowired
    private lateinit var stockRepository: StockRepository

    @BeforeEach
    fun setUp() {
        Stock(productId = 1L, quantity = 100L).let(stockRepository::save)
    }

    @AfterEach
    fun tearDown() {
        stockRepository.deleteAll()
    }

    @Test
    fun `decreaseStock should decrease stock`() {
        stockService.decreaseStock(productId = 1L, quantity = 1L)

        stockRepository.findById(1L).orElseThrow().let {
            assertThat(it.quantity).isEqualTo(99L)
        }
    }

    /**
     * can't pass now. 
     */
    @Test
    fun `multiThread decrease`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            executorService.submit {
                try {
                    stockService.decreaseStock(productId = 1L, quantity = 1L)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        stockRepository.findById(1L).orElseThrow().let {
            assertThat(it.quantity).isEqualTo(0L)
        }

    }

}
