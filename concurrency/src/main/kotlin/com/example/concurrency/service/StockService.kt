package com.example.concurrency.service

import com.example.concurrency.repository.StockRepository
import org.springframework.stereotype.Service

@Service
//@Transactional
class StockService(
    private val stockRepository: StockRepository,
) {

    @Synchronized // Synchronized guarantees only on single process. It doesn't work on multiple process.
    fun decreaseStock(productId: Long, quantity: Long) {
        val stock = stockRepository.findById(productId).orElseThrow()
        stock.decreaseStock(quantity)
        stockRepository.save(stock)
    }

}
