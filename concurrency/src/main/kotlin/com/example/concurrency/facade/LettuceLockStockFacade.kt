package com.example.concurrency.facade

import com.example.concurrency.repository.RedisLockRepository
import com.example.concurrency.service.StockService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class LettuceLockStockFacade(
    private val redisLockRepository: RedisLockRepository,
    @Qualifier("stockServiceImpl") private val stockService: StockService
) {

    fun decreaseStock(id: Long, quantity: Long) {
        while (redisLockRepository.lock(id) != true) {
            Thread.sleep(100)
        }
        try {
            stockService.decreaseStock(id, quantity)
        } finally {
            redisLockRepository.unlock(id)
        }
    }

}
