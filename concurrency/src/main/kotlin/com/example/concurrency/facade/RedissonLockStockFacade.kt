package com.example.concurrency.facade

import com.example.concurrency.service.StockService
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedissonLockStockFacade(
    private val redissonClient: RedissonClient,
    @Qualifier("stockServiceImpl") private val stockService: StockService
) {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    fun decreaseStock(id: Long, quantity: Long) {
        val lock = redissonClient.getLock(id.toString())
        try {
            val available = lock.tryLock(5, 1, TimeUnit.SECONDS)

            if (!available) {
                log.warn("Failed to acquire lock for product: $id")
                return
            }
            stockService.decreaseStock(id, quantity)
        } finally {
            lock.unlock()
        }
    }

}
