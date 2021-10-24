package me.yaman.can.webflux.h2console

import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spring.boot.actuator.autoconfigure.ManagementServerProperties

@TestConfiguration
class DefaultConfig {
    @Bean
    fun serverProperties():ServerProperties{
        val properties = ServerProperties()
        properties.port = 8080
        return properties
    }

    @Bean
    fun managementServerProperties(): ManagementServerProperties {
        val managementServerProperties = ManagementServerProperties()
        managementServerProperties.port = 9090
        return managementServerProperties
    }
}