package com.teachboost.pichanga.screens

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
import com.teachboost.pichanga.location.LocationService

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateEventSportScreen(navController: NavHostController) {
    var currentPage = remember { mutableStateOf(1) }
    val totalSteps = 3
    val currentLocation = remember {
        mutableStateOf<LatLng?>(null)
    }

    // local context
    val context = LocalContext.current
    // permissions
    val locationPermissionState =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    LaunchedEffect(locationPermissionState) {
        locationPermissionState.launchPermissionRequest()
    }

    val locationServie = LocationService()

    LaunchedEffect(Unit) {
        val location = locationServie.getUserLocation(context)
        if (location != null) {
            currentLocation.value = LatLng(location.latitude, location.longitude)
        } else {
            Toast.makeText(context, "No se pudo obtener la ubicacion actual", Toast.LENGTH_SHORT)
                .show()
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.green_light),
            elevation = AppBarDefaults.TopAppBarElevation
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${if (currentPage.value != 1) "Regresar" else "Cancelar"}",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            if (currentPage.value != 1) {
                                currentPage.value = currentPage.value - 1
                            } else {
                                // navegamos al home
                            }
                        })
                Text(text = "Paso ${currentPage.value}", fontWeight = FontWeight.Bold)
                Text(text = "${if (currentPage.value != totalSteps) "Siguiente" else "Crear"}",
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
    }, content = {
        when (currentPage.value) {
            1 -> {
//                    StepOne(onNextStep = { currentPage.value = 2 })
                StepOne()
            }

            2 -> {
                StepTwo(onNextStep = { currentPage.value = 3 }, currentLocation.value!!)
            }

            3 -> {
                StepThree(onNextStep = { currentPage.value = 4 })
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun StepOne() {

    var tituloEvento by remember { mutableStateOf("") }
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val sports = listOf("Futbol 7", "Futbol 11", "Voley")
    var dia by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("60") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Text(text = "Datos generales", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Titulo del evento", modifier = Modifier.padding(bottom = 5.dp))
        OutlinedTextField(
            placeholder = {
                Text(text = "Escribe el nombre de tu evento")
            },
            value = tituloEvento,
            onValueChange = { tituloEvento = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Deporte", modifier = Modifier.padding(bottom = 5.dp))
        OutlinedTextField(value = selectedText,
            placeholder = {
                Text(text = "Elige un deporte")
            },
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth())
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            sports.forEach { sport ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    selectedText = sport
                }) {
                    Text(text = sport)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Dia y Hora", modifier = Modifier.padding(bottom = 5.dp))
        OutlinedTextField(value = "Dia:  $dia",
            onValueChange = {},
            modifier = Modifier
                .clickable { }
                .fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "Hora:  $hora",
            onValueChange = {},
            modifier = Modifier
                .clickable { }
                .fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Duración", modifier = Modifier.padding(bottom = 5.dp))
            OutlinedTextField(
                value = duracion, onValueChange = { duracion = it }, modifier = Modifier
                    .width(60.dp)
                    .height(30.dp)
            )
        }

    }
}

@Composable
fun StepTwo(onNextStep: () -> Unit, currentLocation: LatLng) {
    // provincia
    var selectedTextProvincia by remember { mutableStateOf("") }
    var expandedDrop1 by remember { mutableStateOf(false) }
    val provincias = listOf("Camaná", "Caraveli", "Castilla", "Arequipa")

    // distrito
    var selectedTextDitrito by remember { mutableStateOf("") }
    var expandedDrop2 by remember { mutableStateOf(false) }
    val distritos = listOf("Cerro Colorado", "Socabaya", "Paucarpata", "Cayma")

    // remember location
    val draggedMaker = rememberMarkerState(position = currentLocation)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Text(text = "Datos del lugar", modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Provincia", modifier = Modifier.padding(bottom = 5.dp))
        OutlinedTextField(value = selectedTextProvincia,
            placeholder = {
                Text(text = "Elige una provincia")
            },
            onValueChange = { selectedTextProvincia = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expandedDrop1 = true }
                .fillMaxWidth())
        DropdownMenu(
            expanded = expandedDrop1,
            onDismissRequest = { expandedDrop1 = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            provincias.forEach { provincia ->
                DropdownMenuItem(onClick = {
                    expandedDrop1 = false
                    selectedTextProvincia = provincia
                }) {
                    Text(text = provincia)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Distrito", modifier = Modifier.padding(bottom = 5.dp))
        OutlinedTextField(value = selectedTextDitrito,
            placeholder = {
                Text(text = "Elige un distrito")
            },
            onValueChange = { selectedTextDitrito = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expandedDrop2 = true }
                .fillMaxWidth())
        DropdownMenu(
            expanded = expandedDrop2,
            onDismissRequest = { expandedDrop2 = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            distritos.forEach { distrito ->
                DropdownMenuItem(onClick = {
                    expandedDrop2 = false
                    selectedTextDitrito = distrito
                }) {
                    Text(text = distrito)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Elige tu ubicación exacta", modifier = Modifier.padding(bottom = 16.dp))
        Box(
            modifier = Modifier
                .padding(10.dp)
                .height(300.dp)
        ) {

            val cameraPositionState = rememberCameraPositionState {
                //position = CameraPosition.fromLatLngZoom(LatLng(-16.3988900, -71.5350000), 10f)
                position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = draggedMaker,
                    draggable = true,
                )
            }
        }
//        Column() {
//            Text(text = draggedMaker.position?.latitude.toString())
//            Text(text = draggedMaker.position?.longitude.toString())
//        }
    }
}


@Composable
fun StepThree(onNextStep: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Paso 3")
    }
}