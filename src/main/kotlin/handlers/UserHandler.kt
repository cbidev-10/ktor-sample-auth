package handlers

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import models.dto.TokenResponseDto
import models.dto.UserRequestDto
import models.dto.toResponseDto
import models.dto.toUser
import usecases.UserUseCases

class UserHandler(
    private val userUseCases: UserUseCases,
) {
    fun Route.userRoutes() {
        route("/users") {

            post {
                val userAgent = call.request.headers["User-Agent-Valid"].toString()
                if (userAgent.isEmpty()) {
                    call.respond(HttpStatusCode.Forbidden)
                }
                val user = call.receive<UserRequestDto>()
                val newUser = userUseCases.createUser(user.toUser())
                call.respond(HttpStatusCode.Created, newUser.toResponseDto())
            }

            put("/{id}") {
                val userAgent = call.request.headers["User-Agent-Valid"].toString()
                if (userAgent.isEmpty()) {
                    call.respond(HttpStatusCode.Forbidden)
                }
                val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)

                val dto = call.receive<UserRequestDto>()
                val toUpdate = dto.toUser().copy(id = id)
                val updated = userUseCases.updateUser(toUpdate)
                if (updated != null)
                    call.respond(updated.toResponseDto())
                else
                    call.respond(HttpStatusCode.NotFound)
            }

            post("/authenticate") {
                val dto = call.receive<UserRequestDto>()
                val response = userUseCases.authenticate(dto.toUser())
                call.respond(TokenResponseDto(response))
            }

            get("/validate") {
                val token = call.request.headers["Authorization"].toString()
                val validToken = userUseCases.validateToken(token)
                if (validToken) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }
        }
    }
}
