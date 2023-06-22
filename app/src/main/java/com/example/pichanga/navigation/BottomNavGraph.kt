package com.example.pichanga.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pichanga.screens.AccountScreen
import com.example.pichanga.screens.CreateEventSportScreen
import com.example.pichanga.screens.HomeScreen
import com.example.pichanga.screens.SearchEventSportScreen

@Composable
fun BottomNavGraph( navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Account.route){
            AccountScreen(navController)
        }
        composable(route = BottomBarScreen.CreateEventSport.route){
            CreateEventSportScreen(navController)
        }
        composable(route = BottomBarScreen.SearchEventSPort.route){
            SearchEventSportScreen(navController)
        }
    }
}