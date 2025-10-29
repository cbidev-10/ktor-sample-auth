import handlers.UserHandler
import org.koin.core.qualifier.named
import org.koin.dsl.module
import repositories.IUserRepository
import repositories.impl.UserRepositoryMapDbImpl
import usecases.UserUseCases

val appModule = module {
    single<IUserRepository> {
        UserRepositoryMapDbImpl(get(named("usersDb")))
    }

    single {
        UserUseCases(get())
    }

    single {
        UserHandler(get())
    }
}
