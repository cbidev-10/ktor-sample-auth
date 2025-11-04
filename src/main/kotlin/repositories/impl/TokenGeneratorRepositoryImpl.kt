package repositories.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import repositories.ITokenGeneratorRepository

class TokenGeneratorRepositoryImpl(
    private val client: HttpClient,
    private val tokens: MutableList<String> = mutableListOf()
) : ITokenGeneratorRepository {

    companion object {
        const val URL = "https://www.uuidgenerator.net/api/version1"
    }

    override suspend fun generateToken(): String {
        val response = client.get(URL)
        return response.body<List<String>>().first().also { tokens.add(it) }
    }

    override suspend fun validateToken(token: String): Boolean {
        return tokens.contains(token)
    }
}
