package repositories

interface ITokenGeneratorRepository {
    suspend fun generateToken(): String
    suspend fun validateToken(token: String): Boolean
}
