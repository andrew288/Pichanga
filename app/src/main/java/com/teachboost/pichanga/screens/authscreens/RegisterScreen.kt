package com.teachboost.pichanga.screens.authscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.teachboost.pichanga.R
import com.teachboost.pichanga.components.ButtonCustom
import com.teachboost.pichanga.components.TextFieldCustom
import com.teachboost.pichanga.navigation.appnavigation.Routes

@Composable
fun RegisterScreen(navController: NavHostController) {
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var confirmPassWord = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrarse",
            style = TextStyle(fontSize = 24.sp, color = colorResource(id = R.color.green_dark)),
            modifier = Modifier.padding(16.dp)
        )
        TextFieldCustom(
            textFieldValue = firstName,
            textLabel = "Nombres",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp
        )
        TextFieldCustom(
            textFieldValue = lastName,
            textLabel = "Apellidos",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp
        )
        TextFieldCustom(
            textFieldValue = email,
            textLabel = "Email",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp
        )
        TextFieldCustom(
            textFieldValue = password,
            textLabel = "Contraseña",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp,
            visualTransformation = PasswordVisualTransformation()
        )
        TextFieldCustom(
            textFieldValue = confirmPassWord,
            textLabel = "Confirmar contraseña",
            keyboardType = KeyboardType.Text,
            paddingCustom = 5.dp,
            visualTransformation = PasswordVisualTransformation()
        )
        ButtonCustom(text = "Registrarse", colorText = Color.White, onClick = {
            navController.navigate(Routes.ScreenMain.route)
        })
        Text(
            text = "¿Ya tienes una cuenta? Iniciar sesion",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Routes.ScreenLogin.route) }
        )
    }
}