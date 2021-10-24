package me.yaman.can.webflux.h2console

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.http.HttpHeaders
import java.net.InetSocketAddress

class H2ConsoleAutoConfigurationTests {
    private val contextRunner: ApplicationContextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(H2ConsoleAutoConfiguration::class.java))

    @Test
    fun enableH2ConsoleConfiguration() {
        contextRunner.withUserConfiguration(DefaultConfig::class.java)
            .withPropertyValues("spring.h2.console.enabled:true").run { context ->
                val autoConfig = context.getBean(H2ConsoleAutoConfiguration::class.java)
                assertThat(autoConfig.enabled).isEqualTo(true)
                assertThat(autoConfig.h2ConsoleUrl?.contains("8082")).isEqualTo(false)
            }
    }

    @Test
    fun portH2ConsoleConfiguration() {
        contextRunner.withUserConfiguration(DefaultConfig::class.java)
            .withPropertyValues(
                "spring.h2.console.enabled:true",
                "spring.h2.console.port:8082").run { context ->
                val autoConfig = context.getBean(H2ConsoleAutoConfiguration::class.java)
                assertThat(autoConfig.h2ConsoleUrl?.contains("8082")).isEqualTo(true)
            }
    }

    @Test
    fun endpointH2ConsoleConfiguration() {
        contextRunner.withUserConfiguration(DefaultConfig::class.java)
            .withPropertyValues(
                "spring.h2.console.enabled:true",
                "spring.h2.console.port:8082").run { context ->
                val autoConfig = context.getBean(H2ConsoleAutoConfiguration::class.java)
                val headers = HttpHeaders()
                headers.host= InetSocketAddress("localhost",8080)
                val h2ConsoleInfo = autoConfig.actuatorEndpoint(headers)
                assertThat(h2ConsoleInfo["enabled"]).isEqualTo(true)
            }
    }
}
