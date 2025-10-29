package models.dto

import models.User

data class UserResponseDto(
    val id: Int
)

fun User.toResponseDto() = UserResponseDto(
    id = id
)
