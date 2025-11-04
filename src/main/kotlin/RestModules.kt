import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import repositories.ITokenGeneratorRepository
import repositories.impl.TokenGeneratorRepositoryImpl

val restModule = module {
    single {
        HttpClient(Apache) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<ITokenGeneratorRepository> { TokenGeneratorRepositoryImpl(get()) }
}