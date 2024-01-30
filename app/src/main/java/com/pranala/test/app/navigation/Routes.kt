package com.pranala.test.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pranala.test.app.ui.screen.home.HomeScreen
import com.pranala.test.app.ui.screen.welcome.WelcomeScreen

sealed class Routes(val routes: String) {
    data object WelcomeScreen : Routes("welcome_screen")
    data object HomeScreen : Routes("home_screen")
}

fun NavGraphBuilder.navGraph(navController: NavController) {
    composable(Routes.WelcomeScreen.routes) { from ->
        WelcomeScreen(navController = navController, navBackStackEntry = from)
    }
    composable(Routes.HomeScreen.routes) { from ->
        HomeScreen(navController = navController, navBackStackEntry = from)
    }
}