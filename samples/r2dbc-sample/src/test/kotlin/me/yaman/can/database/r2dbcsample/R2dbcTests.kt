package me.yaman.can.database.r2dbcsample

import me.yaman.can.database.r2dbcsample.entity.City
import me.yaman.can.database.r2dbcsample.repository.CityRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier

@DataR2dbcTest
@ExtendWith(SpringExtension::class)
class R2dbcTests {
    @Autowired
    lateinit var cityRepository: CityRepository

    @Test
    fun insertTest(){
        cityRepository.save(City(null,"istanbul","Turkey")).`as`(StepVerifier::create).assertNext{
            Assertions.assertNotNull(it.id)
        }.verifyComplete()
    }
}