package com.teachboost.pichanga.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.teachboost.pichanga.R
import com.teachboost.pichanga.navigation.bottombar.BottomBarScreen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomBarScreen>
){
    val currentRoute = currentRoute(navController = navController)
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.green_jade)
    ) {
        items.forEach{
                screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                },
                alwaysShowLabel = false)
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}