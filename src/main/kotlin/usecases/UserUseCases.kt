package usecases

import models.User
import repositories.ITokenGeneratorRepository
import repositories.IUserRepository

class UserUseCases(
    private val userRepository: IUserRepository,
    private val tokenGeneratorRepository: ITokenGeneratorRepository
) {

    fun createUser(user: User): User {
        return userRepository.create(user)
    }

    fun updateUser(user: User): User? {
        return userRepository.update(user)
    }

    suspend fun authenticate(user: User): String {
        userRepository.get(user) ?: throw Exception("User [${user.username}] not found")
        return tokenGeneratorRepository.generateToken()
    }
}