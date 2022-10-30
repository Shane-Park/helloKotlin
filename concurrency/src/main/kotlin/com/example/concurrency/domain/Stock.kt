package com.example.concurrency.domain

import javax.persistence.*

@Entity
class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var productId: Long,
    var quantity: Long,
) {
    @Version
    var version: Long? = null

    fun decreaseStock(quantity: Long) {
        if (this.quantity < quantity) {
            throw RuntimeException("Not enough stock")
        }
        this.quantity -= quantity
    }
}
