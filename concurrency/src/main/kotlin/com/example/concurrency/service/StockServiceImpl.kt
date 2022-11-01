package com.example.concurrency.service

import com.example.concurrency.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StockServiceImpl(
    private val stockRepository: StockRepository,
) : StockService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun decreaseStock(id: Long, quantity: Long) {
        val stock = stockRepository.findById(id).orElseThrow()
        stock.decreaseStock(quantity)
        stockRepository.save(stock)
    }

}
