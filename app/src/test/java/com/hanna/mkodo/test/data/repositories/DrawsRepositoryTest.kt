package com.hanna.mkodo.test.data.repositories

import com.hanna.mkodo.test.data.datasource.db.DrawDao
import com.hanna.mkodo.test.data.datasource.network.Api
import com.hanna.mkodo.test.data.responses.DrawsResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DrawsRepositoryTest {

    private lateinit var repository: DrawsRepository

    private val api: Api = mockk()
    private val drawDao: DrawDao = mockk()

    @Before
    fun setUp() {// Given
        repository = DrawsRepository(api, drawDao)
    }

    @Test
    fun `fetchDraws throws an error, fetchDraws isFailure`() = runBlocking {
        coEvery { api.fetchDraws() } throws (Exception("Testing error"))

        val result = repository.fetchDraws()

        assertTrue(result.isFailure)
    }

    @Test
    fun `fetchDraws throws an error, result message matched thrown message`() = runBlocking {
        coEvery { api.fetchDraws() } throws (Exception("Testing error"))

        val result = repository.fetchDraws()

        assertTrue(result.exceptionOrNull()?.message == "Testing error")
    }

    @Test
    fun `fetchDraws returns successful result`() = runBlocking {
        coEvery { api.fetchDraws() }.returns(DrawsResponse(listOf()))

        val result = repository.fetchDraws()

        assertTrue(result.isSuccess)
    }
}