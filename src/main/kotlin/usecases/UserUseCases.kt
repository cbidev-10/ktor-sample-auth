package usecases

import models.User
import repositories.IUserRepository

class UserUseCases(private val userRepository: IUserRepository) {

    fun createUser(user: User): User {
        return userRepository.create(user)
    }

    fun updateUser(user: User): User? {
        return userRepository.update(user)
    }

    fun validateUser(user: User): Boolean {
        val validUser = userRepository.get(user)
        return validUser != null
    }
}