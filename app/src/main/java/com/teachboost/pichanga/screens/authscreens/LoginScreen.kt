package com.teachboost.pichanga.screens.authscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import com.teachboost.pichanga.R
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.teachboost.pichanga.components.ButtonCustom
import com.teachboost.pichanga.components.TextFieldCustom
import com.teachboost.pichanga.navigation.appnavigation.Routes
@Composable
fun LoginScreen(navController: NavHostController) {
    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Iniciar sesion",
            style = TextStyle(fontSize = 24.sp, color = colorResource(id = R.color.green_dark)),
            modifier = Modifier.padding(16.dp)
        )
        TextFieldCustom(
            textFieldValue = email,
            textLabel = "Email",
            keyboardType = KeyboardType.Text,
        )
        TextFieldCustom(
            textFieldValue = password,
            textLabel = "Password",
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        ButtonCustom(
            text = "Ingresar",
            colorText = Color.White,
            onClick = {
                navController.navigate(Routes.ScreenMain.route)
            })

        Text(
            text = "¿No tienes una cuenta? Regístrate",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Routes.ScreenRegister.route) }
        )
    }

}
