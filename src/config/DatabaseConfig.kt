package com.github.ipan97.config

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.HoconApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Database

/**
 * @author Ipan Taufik Rahman
 */
object DatabaseConfig {

    @KtorExperimentalAPI
    private val properties = HoconApplicationConfig(ConfigFactory.load())

    @KtorExperimentalAPI
    private fun hikariDatasource(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = properties.property("ktor.datasource.driver").getString()
        config.jdbcUrl = properties.property("ktor.datasource.url").getString()
        config.username = properties.property("ktor.datasource.username").getString()
        config.password = properties.property("ktor.datasource.password").getString()
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    @KtorExperimentalAPI
    fun init() {
        Database.connect(hikariDatasource())
    }
}
