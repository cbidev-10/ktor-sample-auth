package routes

import handlers.UserHandler
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.registerRoutes() {
    val userHandler: UserHandler by inject()

    routing {
        with(userHandler) {
            userRoutes()
        }
    }
}