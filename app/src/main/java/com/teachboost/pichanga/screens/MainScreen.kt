package com.teachboost.pichanga.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.teachboost.pichanga.components.BottomNavigationBar
import com.teachboost.pichanga.navigation.bottombar.BottomBarScreen
import com.teachboost.pichanga.navigation.bottombar.BottomNavGraph
import com.teachboost.pichanga.screens.viewmodel.AuthViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navControllerApp: NavHostController,
    viewModelAuth: AuthViewModel
) {
    val navController = rememberNavController()
    val navigationItems = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Account
    )
    Scaffold(bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) }){
        BottomNavGraph(navController = navController, navControllerApp, viewModelAuth)
    }

}