package repositories

interface ITokenGeneratorRepository {
    suspend fun generateToken(): String
}
