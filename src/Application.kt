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
import io.ktor.auth.Authentication
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.thymeleaf.Thymeleaf
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }

    install(Authentication) {
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

