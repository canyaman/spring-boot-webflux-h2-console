package me.yaman.can.database.jdbcsample.repository

import me.yaman.can.database.jdbcsample.entity.City
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository: CrudRepository<City, Long>{
    fun findByName(name: String): City?
}