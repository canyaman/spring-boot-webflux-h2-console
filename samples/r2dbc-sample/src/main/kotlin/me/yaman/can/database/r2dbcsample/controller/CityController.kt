package me.yaman.can.database.r2dbcsample.controller

import me.yaman.can.database.r2dbcsample.service.CityService
import me.yaman.can.database.r2dbcsample.entity.City
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping(path = ["/cities"])
class CityController(val cityService: CityService) {
    @GetMapping("/{name}")
    fun getCity(@PathVariable name: String): Mono<City?> {
        return cityService.getCity(name)
    }
}