package handlers

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
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
                val user = call.receive<UserRequestDto>()
                val newUser = userUseCases.createUser(user.toUser())
                call.respond(HttpStatusCode.Created, newUser.toResponseDto())
            }

            put("/{id}") {
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
                val response = userUseCases.validateUser(dto.toUser())
                call.respond(response)
            }
        }
    }
}
