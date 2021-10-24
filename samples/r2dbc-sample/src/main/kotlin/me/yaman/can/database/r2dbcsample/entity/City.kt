package me.yaman.can.database.r2dbcsample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class City(
    @Id
    val id:Long?,
    val name:String,
    val country:String
    )
