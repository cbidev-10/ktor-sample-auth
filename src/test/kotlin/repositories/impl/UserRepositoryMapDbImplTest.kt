package repositories.impl

import models.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserRepositoryMapDbImplTest {

    private lateinit var map: MutableMap<Int, User>
    private lateinit var repo: UserRepositoryMapDbImpl

    @BeforeTest
    fun setUp() {
        map = mutableMapOf()
        repo = UserRepositoryMapDbImpl(map)
    }

    @Test
    fun `create should assign id and store the user`() {
        val s = User(username = "user", password = "1234")
        val created = repo.create(s)

        assertEquals(1, created.id)
        assertEquals(s.username, created.username)
        assertEquals(s.password, created.password)
    }

    @Test
    fun `get should return correct user by id`() {
        val s = repo.create(User(username = "user", password = "1234"))
        val found = repo.get(s)
        assertEquals(s, found)
    }

    @Test
    fun `update should change existing user`() {
        val s = repo.create(User(username = "user", password = "1234"))
        val updated = repo.update(s.copy(password = "Nuevo"))
        assertNotNull(updated)
        assertEquals("Nuevo", updated?.password)
    }
}
