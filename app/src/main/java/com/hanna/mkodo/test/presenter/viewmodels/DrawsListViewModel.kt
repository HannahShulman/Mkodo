package com.hanna.mkodo.test.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanna.mkodo.test.data.repositories.DrawsRepository
import com.hanna.mkodo.test.domain.models.Draw
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DrawsListViewModel(private val repository: DrawsRepository) : ViewModel(), KoinComponent {

    private val _drawsListScreenUiState = MutableStateFlow(DrawsListScreenUiState(false, null, null))
    val drawsListScreenUiState: StateFlow<DrawsListScreenUiState> = _drawsListScreenUiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchDraws()
        }
    }

    suspend fun fetchDraws() {
        _drawsListScreenUiState.value = _drawsListScreenUiState.value.copy(isLoading = true, errorMessage = null)
        val list = repository.fetchDraws()
        _drawsListScreenUiState.value = _drawsListScreenUiState.value.copy(
            isLoading = false,
            drawsList = list.getOrNull(),
            errorMessage = list.exceptionOrNull()?.message
        )
    }
}

data class DrawsListScreenUiState(val isLoading: Boolean, val drawsList: List<Draw>?, val errorMessage: String?)