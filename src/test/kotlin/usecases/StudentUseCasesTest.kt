package usecases

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import models.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import repositories.IUserRepository
import kotlin.test.Test

class UserUseCasesMockTest {

    private val repo = mockk<IUserRepository>(relaxed = true)
    private val useCases = UserUseCases(repo)

    @Test
    fun `createUser should call repo create and return new User`() {
        val fakeUser = User(username = "test", password = "1234")
        coEvery { repo.create(fakeUser) } returns fakeUser.copy(id = 42)

        val result = useCases.createUser(fakeUser)

        assertEquals(42, result.id)

        coVerify(exactly = 1) {
            repo.create(fakeUser)
        }
    }


    @Test
    fun `updateUser should call repo update and return updated user`() {
        val s = User(username = "test", password = "1234")
        coEvery { repo.update(s) } returns s.copy(password = "12345")

        val updated = useCases.updateUser(s)
        assertEquals("12345", updated?.password)

        coVerify { repo.update(s) }
    }

    @Test
    fun `validateUser should call repo and return valid user`() {
        val s = User(username = "test", password = "1234")
        coEvery { repo.get(s) } returns s

        val validUser = useCases.validateUser(s)

        assertTrue(validUser)

        coVerify { repo.get(s) }
    }
}
