package com.example.concurrency.facade

import com.example.concurrency.service.OptimisticLockStockService
import org.springframework.stereotype.Service

@Service
class OptimisticLockStockFacade(
    private val stockService: OptimisticLockStockService,
) {

    fun decreaseStock(id: Long, quantity: Long) {
        while (true) {
            try {
                stockService.decreaseStock(id, quantity)
                break
            } catch (e: Exception) {
                Thread.sleep(100)
                // retry
            }
        }
    }

}
