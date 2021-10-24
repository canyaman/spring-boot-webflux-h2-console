package me.yaman.can.database.r2dbcsample.repository

import me.yaman.can.database.r2dbcsample.entity.City
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface CityRepository: ReactiveCrudRepository<City, Long> {
    fun findByName(name: String): Mono<City?>
}