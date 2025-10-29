package models.dto

import models.User

data class UserRequestDto(
    val username: String,
    val password: String,
)

fun UserRequestDto.toUser(id: Int = 0) = User(
    id = id,
    username = this.username,
    password = this.password,
)
