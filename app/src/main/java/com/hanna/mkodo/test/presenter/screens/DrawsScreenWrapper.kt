package com.hanna.mkodo.test.presenter.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hanna.mkodo.test.presenter.routes.DrawDetailDestination
import com.hanna.mkodo.test.presenter.routes.DrawListDestination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawsScreenWrapper() {//this wraps round all destinations related to the draws(list and details
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DrawListDestination.getNavRoute(),
        modifier = Modifier.padding()
    ) {
        composable(route = DrawListDestination.getNavRoute()) {
            DrawsListScreen(navHostController = navController)
        }
        composable(
            route = DrawDetailDestination.getNavRoute(),
            arguments = listOf(navArgument("drawId") { type = NavType.StringType })
        ) {
            DrawDetailScreen(it.arguments?.getString("drawId") ?: "SOmething went wrong")
        }
    }
}