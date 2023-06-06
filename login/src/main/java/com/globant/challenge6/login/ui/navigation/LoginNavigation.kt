package com.globant.challenge6.login.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.globant.challenge6.login.ui.screens.LoginScreen
import com.globant.challenge6.login.ui.screens.SplashScreen

@Composable
fun LoginNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginScreens.SplashScreen.name
    ) {
        composable(LoginScreens.SplashScreen.name) {
            SplashScreen(
                loginViewModel = hiltViewModel()
            ) {
                val route = "route"
                navController.navigate(route)
            }
        }
        composable(LoginScreens.LoginScreen.name) {
            LoginScreen(
                loginViewModel = hiltViewModel()
            ) {
                val route = "route"
                navController.navigate(route)
            }
        }
    }
}