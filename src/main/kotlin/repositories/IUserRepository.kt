package repositories

import models.User

interface IUserRepository {
    fun create(user: User): User
    fun update(user: User): User?
    fun get(user: User): User?
}
