package repositories.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import repositories.ITokenGeneratorRepository

class TokenGeneratorRepositoryImpl(
    private val client: HttpClient
) : ITokenGeneratorRepository {

    override suspend fun generateToken(): String {
        return client.get("https://www.uuidgenerator.net/api/version1").bodyAsText().trim()
    }
}
