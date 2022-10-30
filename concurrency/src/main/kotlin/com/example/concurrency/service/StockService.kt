package com.example.concurrency.service

import com.example.concurrency.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StockService(
    private val stockRepository: StockRepository,
) {

    fun decreaseStock(productId: Long, quantity: Long) {
        val stock = stockRepository.findById(productId).orElseThrow()
        stock.decreaseStock(quantity)
    }

}
