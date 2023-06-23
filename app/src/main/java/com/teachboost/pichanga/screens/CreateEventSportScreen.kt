package com.teachboost.pichanga.screens

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teachboost.pichanga.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateEventSportScreen(navController: NavHostController) {
    var currentPage = remember{ mutableStateOf(1) }
    val totalSteps = 3
    val currentLocation = remember {
        mutableStateOf<LatLng?>(null)
    }

    // local context
    val context = LocalContext.current
    // permissions
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    LaunchedEffect(locationPermissionState){
        locationPermissionState.launchPermissionRequest()
    }

    fusedLocationClient.lastLocation.addOnSuccessListener {
            location ->
        if(location != null){
            println("Entro entro")
            currentLocation.value = LatLng(location.latitude, location.longitude)
        }else {
            Toast.makeText(context, "No se pudo obtener la ubicacion actual", Toast.LENGTH_SHORT).show()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.green_light),
                elevation = AppBarDefaults.TopAppBarElevation
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${if (currentPage.value != 1) "Regresar" else "Cancelar"}",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable {
                                if (currentPage.value != 1) {
                                    currentPage.value = currentPage.value - 1
                                } else {
                                    // navegamos al home
                                }
                            }
                    )
                    Text(text = "Paso ${currentPage.value}", fontWeight = FontWeight.Bold)
                    Text(
                        text = "${if (currentPage.value != totalSteps) "Siguiente" else "Crear"}",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clickable {
                                if (currentPage.value != totalSteps) {
                                    currentPage.value = currentPage.value + 1
                                } else {
                                    // creamos el evento
                                }
                            })
                }
            }
        },
        content = {
            when(currentPage.value){
                1 -> {
                    StepOne(onNextStep = {currentPage.value = 2})
                }
                2 -> {
                    StepTwo(onNextStep = {currentPage.value = 3}, currentLocation.value!!)
                }
                3 -> {
                    StepThree(onNextStep = {currentPage.value = 4})
                }
            }
        }
    )
}

@Composable
fun StepOne(onNextStep: () -> Unit){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Paso 1")
    }
}

@Composable
fun StepTwo(onNextStep: () -> Unit, currentLocation: LatLng){
    // remember location
    val draggedMaker = rememberMarkerState(position = currentLocation)
    Column() {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .height(300.dp)
        ) {

            val cameraPositionState = rememberCameraPositionState{
                //position = CameraPosition.fromLatLngZoom(LatLng(-16.3988900, -71.5350000), 10f)
                position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
            }
            GoogleMap(modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState){
                Marker(
                    state = draggedMaker,
                    draggable = true,
                )
            }
        }
        Column() {
            Text(text = draggedMaker.position?.latitude.toString())
            Text(text = draggedMaker.position?.longitude.toString())
        }
    }
}


@Composable
fun StepThree(onNextStep: () -> Unit){
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Paso 3")
    }
}