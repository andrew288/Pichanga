package com.teachboost.pichanga.navigation.bottombar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.teachboost.pichanga.navigation.bottombar.BottomBarScreen
import com.teachboost.pichanga.screens.AccountScreen
import com.teachboost.pichanga.screens.CreateEventSportScreen
import com.teachboost.pichanga.screens.HomeScreen
import com.teachboost.pichanga.screens.SearchEventSportScreen

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