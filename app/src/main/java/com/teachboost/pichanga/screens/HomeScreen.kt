package com.teachboost.pichanga.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teachboost.pichanga.R
import com.teachboost.pichanga.navigation.bottombar.BottomBarScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green_jade))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painterResource(id = R.drawable.icon_search_event),
                        contentDescription = "Buscar",
                        tint = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(text = "Buscar eventos")
                }
            }
            Button(
                onClick = { navController.navigate(BottomBarScreen.CreateEventSport.route) },
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green_jade))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painterResource(id = R.drawable.icon_sports),
                        contentDescription = "Deporte",
                        tint = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(text = "Organizar partido")
                }
            }
        }
    }
}