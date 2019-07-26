package com.github.ipan97

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.github.ipan97.repository.PostRepository
import com.github.ipan97.router.api
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.thymeleaf.Thymeleaf
import org.koin.ktor.ext.Koin
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver


fun Application.module() {

    install(Koin) {
        modules(koinModule)
    }

    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
            registerModule(JavaTimeModule())
            registerModule(JodaModule())
        }
    }

    val postRepository = PostRepository()

    install(Routing) {
        api(postRepository)
    }
}

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args))
        .start(wait = true)
}

