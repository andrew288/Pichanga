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

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.ScreenStart.route
) {
    NavHost(navController = navController, startDestination = startDestination){
        composable(Routes.ScreenLogin.route){
            LoginScreen(navController)
        }
        composable(Routes.ScreenRegister.route){
            RegisterScreen(navController)
        }
        composable(Routes.ScreenMain.route){
            MainScreen(navController)
        }
        composable(Routes.ScreenStart.route){
            StartScreen(navController)
        }
    }

}