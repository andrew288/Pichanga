package com.teachboost.pichanga.navigation.appnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teachboost.pichanga.screens.authscreens.LoginScreen
import com.teachboost.pichanga.screens.MainScreen
import com.teachboost.pichanga.screens.StartScreen
import com.teachboost.pichanga.screens.authscreens.RegisterScreen
import com.teachboost.pichanga.screens.viewmodel.AuthViewModel

@Composable
fun AppNavHost(
    viewModel: AuthViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.ScreenStart.route
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(Routes.ScreenLogin.route){
            LoginScreen(navController, viewModel)
        }
        composable(Routes.ScreenRegister.route){
            RegisterScreen(navController, viewModel)
        }
        composable(Routes.ScreenMain.route){
            MainScreen(navController, viewModel)
        }
        composable(Routes.ScreenStart.route){
            StartScreen(navController, viewModel)
        }
    }

}