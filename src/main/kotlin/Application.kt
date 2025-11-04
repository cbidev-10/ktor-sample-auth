import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import routes.registerRoutes

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(appModule, mapDbModule, restModule)
    }

    install(ContentNegotiation) {
        jackson()
    }

    registerRoutes()
    configureWarmup()
}