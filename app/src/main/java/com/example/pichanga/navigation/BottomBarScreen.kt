package com.example.pichanga.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Account: BottomBarScreen(
        route = "ACCOUNT",
        title = "Account",
        icon = Icons.Default.AccountCircle
    )
    object CreateEventSport: BottomBarScreen(
        route = "CREATE_EVENT_SPORT",
        title = "Create Event SPort",
        icon = Icons.Default.Create
    )
    object SearchEventSPort: BottomBarScreen(
        route = "SEARCH_EVENT_SPORT",
        title = "Search Event SPort",
        icon = Icons.Default.Search
    )
}