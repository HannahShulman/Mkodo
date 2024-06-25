package com.hanna.mkodo.test.presenter.viewmodels

import androidx.lifecycle.ViewModel
import com.hanna.mkodo.test.data.repositories.DrawsRepository
import com.hanna.mkodo.test.domain.models.Draw
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent


class DrawDetailsViewModel(private val repository: DrawsRepository) : ViewModel(), KoinComponent {

    private val _drawDetailsScreenUiState = MutableStateFlow(DrawsDetailScreenUiState(false, emptyList(),null, null))
    val drawDetailsScreenUiState: StateFlow<DrawsDetailScreenUiState> = _drawDetailsScreenUiState.asStateFlow()

    suspend fun fetchDrawById(id: String) {
        _drawDetailsScreenUiState.value = _drawDetailsScreenUiState.value.copy(isLoading = true, errorMessage = null)
        val listResult = repository.fetchDraws()
        val result = repository.fetchDrawById(id)
        _drawDetailsScreenUiState.value = _drawDetailsScreenUiState.value.copy(
            isLoading = false,
            drawsList = listResult.getOrNull().orEmpty(),
            draw = result.getOrNull(),
            errorMessage = result.exceptionOrNull()?.message
        )
    }
}

data class DrawsDetailScreenUiState(val isLoading: Boolean,  val drawsList: List<Draw>?, val draw: Draw?, val errorMessage: String?)