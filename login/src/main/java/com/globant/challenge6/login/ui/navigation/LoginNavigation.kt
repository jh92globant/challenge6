package com.globant.challenge6.login.ui.navigation

import androidx.compose.runtime.Composable
/*
@Composable
fun MusicNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(LoginScreens.SplashScreen.name) {
            SplashScreen(
                loginViewModel = hiltViewModel()
            ) { route ->
                navController.navigate(route)
            }
        }
        composable(LoginScreens.LoginScreen.name) {
            LoginScreen(
                artistViewModel = hiltViewModel(),
                artistId = it.arguments?.getInt("artistId") ?: 0
            ) { route ->
                navController.navigate(route)
            }
        }
    }
}*/