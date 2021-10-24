package me.yaman.can.database.r2dbcsample

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	properties = [
	]
)
@AutoConfigureWebTestClient
class R2dbcApplicationTests() {
	@Autowired
	lateinit var webClient: WebTestClient

	@Test
	fun redirectH2ConsoleRedirect() {
		webClient.get().uri("/h2-console").exchange().expectStatus().is3xxRedirection
	}
}