package me.yaman.can.database.r2dbcsample.service

import me.yaman.can.database.r2dbcsample.entity.City
import me.yaman.can.database.r2dbcsample.repository.CityRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CityService(val cityRepository: CityRepository) {
    fun getCity(name:String): Mono<City?> {
        return cityRepository.findByName(name)
    }
}