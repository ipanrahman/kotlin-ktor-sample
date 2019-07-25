package com.github.ipan97.router

import com.github.ipan97.repository.PostRepository
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

/**
 * @author Ipan Taufik Rahman
 */
fun Routing.api(
    postRepository: PostRepository
) {
    route("/api") {
        route("posts") {
            get("/") { call.respond(postRepository.findAll()) }
            get("/{id}") {
                val id: Int = call.parameters["id"]!!.toInt()
                call.respond(postRepository.findById(id)!!)
            }
        }
    }
}
