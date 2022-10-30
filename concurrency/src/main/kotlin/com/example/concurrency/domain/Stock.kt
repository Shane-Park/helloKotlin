package com.example.concurrency.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var productId: Long,
    var quantity: Long,
) {

    fun decreaseStock(quantity: Long) {
        if (this.quantity < quantity) {
            throw RuntimeException("Not enough stock")
        }
        this.quantity -= quantity
    }
}
