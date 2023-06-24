package com.teachboost.pichanga.screens.authscreens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.teachboost.pichanga.data.auth.Resource
import com.teachboost.pichanga.navigation.appnavigation.Routes
import com.teachboost.pichanga.screens.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel) {
    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }

    val authResource = viewModel?.loginFlow?.collectAsState()
    val context = LocalContext.current

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
                val errorsAuth = validateEmailAndPassword(email.value, password.value)
                if(errorsAuth.isEmpty()){
                    viewModel?.loginUser(email.value, password.value)
                } else {
                    var errors = ""
                    for (error in errorsAuth){
                        errors += error + "\n"
                    }
                    Toast.makeText(context, errors, Toast.LENGTH_LONG).show()
                }
            })

        Text(
            text = "¿No tienes una cuenta? Regístrate",
            style = TextStyle(color = Color.Gray),
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate(Routes.ScreenRegister.route) }
        )

        authResource?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.green_dark)
                    )
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Routes.ScreenMain.route) {
                            popUpTo(Routes.ScreenLogin.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}
fun validateEmailAndPassword(email: String, password: String): List<String> {
    val validationErrors = mutableListOf<String>()

    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=\\S+\$).{8,}\$")

    if (!email.matches(emailRegex)) {
        validationErrors.add("El correo no es válido")
    }

    if (!password.matches(passwordRegex)) {
        validationErrors.add("La contraseña es incorrecta")
    }

    return validationErrors
}
