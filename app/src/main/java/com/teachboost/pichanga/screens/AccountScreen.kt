package com.teachboost.pichanga.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.teachboost.pichanga.R
import com.teachboost.pichanga.data.auth.Resource
import com.teachboost.pichanga.navigation.appnavigation.Routes
import com.teachboost.pichanga.navigation.bottombar.BottomBarScreen
import com.teachboost.pichanga.screens.viewmodel.AuthViewModel

@Composable
fun AccountScreen(
    navController: NavHostController,
    navControllerApp: NavHostController,
    viewModelAuth: AuthViewModel
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Button(onClick = {
            viewModelAuth.logout()
            navControllerApp.navigate(Routes.ScreenStart.route){
                popUpTo(BottomBarScreen.Account.route) { inclusive = true }
            }
        }) {
            Text(text = "Logout", fontWeight = FontWeight.Bold )
        }
    }
}