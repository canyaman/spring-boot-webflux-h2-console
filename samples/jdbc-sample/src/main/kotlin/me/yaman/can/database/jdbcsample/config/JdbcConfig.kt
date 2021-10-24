package me.yaman.can.database.jdbcsample.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@Configuration
@EnableJdbcRepositories("me.yaman.can.database.jdbcsample.repository")
class JdbcConfig {
}