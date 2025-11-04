package usecases

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import models.User
import org.junit.jupiter.api.Assertions.assertEquals
import repositories.ITokenGeneratorRepository
import repositories.IUserRepository
import kotlin.test.Test

class UserUseCasesMockTest {

    private val repo = mockk<IUserRepository>(relaxed = true)
    private val tokenGeneratorRepository = mockk<ITokenGeneratorRepository>(relaxed = true)
    private val useCases = UserUseCases(repo, tokenGeneratorRepository)

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
    fun `validateUser should call repo and return token`() {
        val s = User(username = "test", password = "1234")
        val expectedToken = "ea4cc05e-b9c1-11f0-8de9-0242ac120002"

        coEvery { repo.get(s) } returns s
        coEvery { tokenGeneratorRepository.generateToken() } returns expectedToken

        val token = runBlocking {
            useCases.validateUser(s)
        }

        assertEquals(expectedToken, token)

        coVerify(exactly = 1) {
            repo.get(s)
            tokenGeneratorRepository.generateToken()
        }
    }
}
