package me.yaman.can.database.jdbcsample

import me.yaman.can.database.jdbcsample.entity.City
import me.yaman.can.database.jdbcsample.repository.CityRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJdbcTest
@ExtendWith(SpringExtension::class)
class JdbcTests() {

    @Autowired
    lateinit var cityRepository: CityRepository

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun insertTest(){
        cityRepository.save(City(null,"istanbul","Turkey"))
        Assertions.assertNotNull(cityRepository.findByName("istanbul")?.id)
    }
}