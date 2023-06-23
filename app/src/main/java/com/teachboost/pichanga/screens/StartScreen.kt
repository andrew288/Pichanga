package com.teachboost.pichanga.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teachboost.pichanga.R
import com.teachboost.pichanga.navigation.appnavigation.Routes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartScreen(navController: NavHostController) {
    val sliders = listOf(
        R.drawable.pager1_500x,
        R.drawable.pager2_500x,
        R.drawable.pager3_500x,
        R.drawable.pager4_500x
    )
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = sliders[3]),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.ScreenLogin.route){
                        popUpTo(Routes.ScreenStart.route){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green_light))
            ) {
                Text(text = "LogIn")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    navController.navigate(Routes.ScreenRegister.route){
                        popUpTo(Routes.ScreenStart.route){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green_jade))
            ) {
                Text(text = "SignUp")
            }
        }
    }

}