import models.User
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer

val mapDbModule = module {
    single<DB> {
        DBMaker.memoryDB().make()
    }

    single<MutableMap<Int, User>>(named("usersDb")) {
        get<DB>().hashMap(
            "users",
            Serializer.INTEGER, Serializer.JAVA
        ).createOrOpen() as MutableMap<Int, User>
    }
}