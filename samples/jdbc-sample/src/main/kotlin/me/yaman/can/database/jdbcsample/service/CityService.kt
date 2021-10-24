package me.yaman.can.database.jdbcsample.service

import me.yaman.can.database.jdbcsample.entity.City
import me.yaman.can.database.jdbcsample.repository.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(val cityRepository: CityRepository) {
    fun getCity(name:String): City? {
        return cityRepository.findByName(name)
    }
}