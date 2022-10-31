package com.example.concurrency.facade

import com.example.concurrency.repository.LockRepository
import com.example.concurrency.service.StockService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class NamedLockStockFacade(
    private val lockRepository: LockRepository,
    @Qualifier("namedLockStockService") private val stockService: StockService
) {

    fun decreaseStock(id: Long, quantity: Long) {
        try {
            lockRepository.getLock(id.toString())
            stockService.decreaseStock(id, quantity)
        } finally {
            lockRepository.releaseLock(id.toString())
        }
    }

}
