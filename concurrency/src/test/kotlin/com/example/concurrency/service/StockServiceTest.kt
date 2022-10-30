package com.example.concurrency.service

import com.example.concurrency.domain.Stock
import com.example.concurrency.facade.OptimisticLockStockFacade
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
    private lateinit var synchronizedStockService: StockService

    @Autowired
    private lateinit var pessimisticLockStockService: StockService

    @Autowired
    private lateinit var optimisticLockStockFacade: OptimisticLockStockFacade

    @Autowired
    private lateinit var stockRepository: StockRepository

    var stockId: Long? = null

    @BeforeEach
    fun setUp() {
        val stock = Stock(productId = 1L, quantity = 100L)
        stock.let(stockRepository::save)
        stockId = stock.id
    }

    @AfterEach
    fun tearDown() {
        stockRepository.deleteAll()
    }

    @Test
    fun `decreaseStock should decrease stock`() {
        synchronizedStockService.decreaseStock(id = stockId!!, quantity = 1L)

        stockRepository.findById(stockId!!).orElseThrow().let {
            assertThat(it.quantity).isEqualTo(99L)
        }
    }

    @Test
    fun `multiThread decrease`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            executorService.submit {
                try {
                    synchronizedStockService.decreaseStock(id = stockId!!, quantity = 1L)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        stockRepository.findById(stockId!!).orElseThrow().let {
            assertThat(it.quantity).isEqualTo(0L)
        }
    }

    @Test
    fun `PessimisticLock decrease`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            executorService.submit {
                try {
                    pessimisticLockStockService.decreaseStock(id = stockId!!, quantity = 1L)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        stockRepository.findById(stockId!!).orElseThrow().let {
            assertThat(it.quantity).isEqualTo(0L)
        }
    }

    @Test
    fun `OptimisticLock decrease`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            executorService.submit {
                try {
                    optimisticLockStockFacade.decreaseStock(id = stockId!!, quantity = 1L)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        stockRepository.findById(stockId!!).orElseThrow().let {
            assertThat(it.quantity).isEqualTo(0L)
        }
    }

}
