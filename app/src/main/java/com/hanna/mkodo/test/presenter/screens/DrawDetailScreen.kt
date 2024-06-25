package com.hanna.mkodo.test.presenter.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.hanna.mkodo.test.presenter.viewmodels.DrawDetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalFoundationApi
@Composable
fun DrawDetailScreen(id: String, drawDetailsViewModel: DrawDetailsViewModel = getViewModel()) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "") {
        scope.launch {
            drawDetailsViewModel.fetchDrawById(id)
        }
    }

    val uiState = drawDetailsViewModel.drawDetailsScreenUiState.collectAsState()
    if (uiState.value.isLoading) {
        LoadingScreen()
    } else {
        val pos = uiState.value.drawsList.orEmpty().indexOfFirst { it.id == id }

        val pagerState = rememberPagerState(maxOf(0, pos))

        HorizontalPager(count = uiState.value.drawsList?.size ?: 1, state = pagerState) { page ->
            Column(modifier = Modifier.fillMaxSize()) {
                val selectedDraw = uiState.value.drawsList.orEmpty()[page]
                selectedDraw?.let {
                    Text(text = it.id)
                    Text(text = it.drawDate)
                    Text(text = it.number1)
                    Text(text = it.number2)
                    Text(text = it.number3)
                    Text(text = it.number4)
                    Text(text = it.number5)
                    Text(text = it.number6)
                    Text(text = it.bonusBall)
                    Text(text = it.topPrize.toString())
                }

            }
        }
    }
}
