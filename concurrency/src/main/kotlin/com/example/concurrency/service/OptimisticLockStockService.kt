package com.example.concurrency.service

import com.example.concurrency.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OptimisticLockStockService(
    private val stockRepository: StockRepository,
) : StockService {

    override fun decreaseStock(id: Long, quantity: Long) {
        val stock = stockRepository.findByIdWithOptimisticLock(id).orElseThrow()
        stock.decreaseStock(quantity)
        stockRepository.save(stock)
    }

}
