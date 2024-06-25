package com.hanna.mkodo.test.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.hanna.mkodo.test.domain.models.Draw
import com.hanna.mkodo.test.presenter.viewmodels.DrawsListViewModel
import com.hanna.mkodo.test.presenter.routes.DrawDetailDestination
import org.koin.androidx.compose.getViewModel

@Composable
fun DrawsListScreen(drawsListViewModel: DrawsListViewModel = getViewModel(), navHostController: NavHostController) {

    val drawsListScreenUiState = drawsListViewModel.drawsListScreenUiState.collectAsState()
    val listState = rememberLazyListState()

    if (drawsListScreenUiState.value.isLoading) {
        LoadingScreen()
    } else if (!drawsListScreenUiState.value.drawsList.isNullOrEmpty()) {
        LazyColumn(state = listState) {
            items(drawsListScreenUiState.value.drawsList?.size ?: 0) { item ->
                drawsListScreenUiState.value.drawsList?.get(item)?.let {
                    DrawItem(it) { id -> navHostController.navigate(DrawDetailDestination.createNavWithArgs(it.id)) }
                }
            }
        }
    } else if (!drawsListScreenUiState.value.errorMessage.isNullOrEmpty()) {
        drawsListScreenUiState.value.errorMessage?.let {
            Text(text = it)
        }
    }
}

@Composable
fun DrawItem(draw: Draw, onItemClicked: (id: String) -> Unit) {
    Column(modifier = Modifier.clickable { onItemClicked(draw.id) }) {
        Text(text = draw.drawDate)
    }
}

@Preview(showBackground = true)
@Composable
fun DrawsScreenPreview() {
    DrawItem(
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
        )
    ) { _ -> }
}