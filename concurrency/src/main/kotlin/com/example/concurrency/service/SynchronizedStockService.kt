package com.example.concurrency.service

import com.example.concurrency.repository.StockRepository
import org.springframework.stereotype.Service

@Service
//@Transactional
/**
 * if you remove @Transactional, it will wrap this service class with a proxy class
 * after decrease method, before it actually commit to the DB, other thread can still call decrease method
 * That's why it doesn't work with @Transactional annotation
 */
class SynchronizedStockService(
    private val stockRepository: StockRepository,
) : StockService {

    @Synchronized // Synchronized guarantees only on single process. It doesn't work on multiple process.
    override fun decreaseStock(id: Long, quantity: Long) {
        val stock = stockRepository.findById(id).orElseThrow()
        stock.decreaseStock(quantity)
        stockRepository.save(stock)
    }

}
