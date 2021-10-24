package me.yaman.can.database.jdbcsample.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class City(
    @Id
    val id:Long?,
    val name:String,
    val country:String
    )
