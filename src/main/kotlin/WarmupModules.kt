import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import models.User
import org.koin.ktor.ext.inject
import repositories.IUserRepository

fun Application.configureWarmup() {
    val repository by inject<IUserRepository>()

    environment.monitor.subscribe(ApplicationStarted) {
        listOf(
            User(
                username = "admin1",
                password = "1234"
            ),
            User(
                username = "admin2",
                password = "1234"
            ),
            User(
                username = "admin3",
                password = "1234"
            )
        ).run {
            this.forEach { repository.create(it) }
        }
    }
}
