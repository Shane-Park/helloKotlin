package com.example.concurrency.service

interface StockService {

    fun decreaseStock(id: Long, quantity: Long)
}
