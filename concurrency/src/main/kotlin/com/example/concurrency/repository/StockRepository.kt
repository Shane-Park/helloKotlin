package com.example.concurrency.repository

import com.example.concurrency.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.*
import javax.persistence.LockModeType.*

interface StockRepository : JpaRepository<Stock, Long> {

    @Lock(PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :id")
    fun findByIdWithPessimisticLock(id: Long): Optional<Stock>
}
