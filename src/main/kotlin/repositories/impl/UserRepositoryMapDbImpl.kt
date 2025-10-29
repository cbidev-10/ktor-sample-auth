package repositories.impl

import models.User
import repositories.IUserRepository

class UserRepositoryMapDbImpl(
    private val map: MutableMap<Int, User>,
) : IUserRepository {
    private var lastId = 0

    override fun create(user: User): User {
        val s = user.copy(id = ++lastId)
        map[s.id] = s
        return s
    }

    override fun update(user: User): User? {
        if (!map.containsKey(user.id)) return null
        map[user.id] = user
        return user
    }

    override fun get(user: User): User? {
        return map.values.firstOrNull {
            it.username == user.username && it.password == user.password
        }
    }
}
