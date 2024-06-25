package com.hanna.mkodo.test.presenter

import com.hanna.mkodo.test.data.repositories.DrawsRepository
import com.hanna.mkodo.test.domain.models.Draw
import com.hanna.mkodo.test.presenter.viewmodels.DrawsListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DrawsViewModelTest {

    private val drawsList = listOf(
        Draw(
            id = "draw-1",
            drawDate = "2023-05-15",
            number1 = "2",
            number2 = "16",
            number3 = "23",
            number4 = "44",
            number5 = "47",
            number6 = "52",
            bonusBall = "14",
            topPrize = 4000000000
        ),
        Draw(
            id = "draw-2",
            drawDate = "2023-05-15",
            number1 = "2",
            number2 = "16",
            number3 = "23",
            number4 = "44",
            number5 = "47",
            number6 = "52",
            bonusBall = "14",
            topPrize = 4000000000
        )
    )

    private val repository: DrawsRepository = mockk()
    private lateinit var viewModel: DrawsListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DrawsListViewModel(repository)
    }


    @Test
    fun `viewmodel init triggers fetchDraws`() = runTest {
        coEvery { repository.fetchDraws() } returns Result.success(drawsList)

        // Recreate the viewModel to trigger init block
        viewModel = DrawsListViewModel(repository)

        coVerify { repository.fetchDraws() }
    }

    @Test
    fun `fetchDraws success, updates drawsListScreenUiState`() = runTest {

        coEvery { repository.fetchDraws() } returns Result.success(drawsList)

        viewModel.fetchDraws()

        val state = viewModel.drawsListScreenUiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(drawsList, state.drawsList)
        assertEquals(null, state.errorMessage)
    }


    @Test
    fun `fetchDraws failure updates drawsListScreenUiState`() = runTest {
        val errorMessage = "Error fetching draws"
        coEvery { repository.fetchDraws() } returns Result.failure(Exception(errorMessage))

        viewModel.fetchDraws()

        val state = viewModel.drawsListScreenUiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(null, state.drawsList)
        assertEquals(errorMessage, state.errorMessage)
    }
}