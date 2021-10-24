package me.yaman.can.database.jdbcsample

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	properties = [
		"management.server.port=0",
		"management.endpoints.web.base-path=/actuator"
	]
)
@AutoConfigureWebTestClient
class JdbcApplicationTests() {
	@LocalManagementPort
	var localManagementPort: Int = 0

	var webClient: WebTestClient? = null

	@BeforeEach
	fun setup(){
		webClient = WebTestClient.bindToServer()
			.baseUrl("http://localhost:$localManagementPort")
			.build();
	}

	@Test
	fun actuatorH2ConsoleEndpoint() {
		webClient!!.get().uri("/actuator/h2console").exchange().expectBody().jsonPath("enabled").isEqualTo(true)
	}

	@Test
	fun actuatorH2ConsoleRedirect() {
		webClient!!.get().uri("/actuator/h2console/redirect").exchange().expectStatus().is3xxRedirection
	}
}
