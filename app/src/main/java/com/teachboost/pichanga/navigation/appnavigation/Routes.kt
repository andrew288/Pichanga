package com.teachboost.pichanga.navigation.appnavigation

sealed class Routes(val route: String) {
    object ScreenLogin: Routes("LoginScreen")
    object ScreenRegister: Routes("RegisterScreen")
    object ScreenMain: Routes("MainScreen")
    object ScreenStart: Routes("StartScreen")
}