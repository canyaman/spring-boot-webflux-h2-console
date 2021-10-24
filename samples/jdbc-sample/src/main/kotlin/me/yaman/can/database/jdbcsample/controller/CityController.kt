package me.yaman.can.database.jdbcsample.controller

import me.yaman.can.database.jdbcsample.entity.City
import me.yaman.can.database.jdbcsample.service.CityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/cities"])
class CityController(val cityService: CityService) {
    @GetMapping("/{name}")
    fun getCity(@PathVariable name: String): City? {
        return cityService.getCity(name)
    }
}