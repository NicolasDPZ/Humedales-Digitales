package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.R
import com.example.humedalesdigitales.componentes.BotAppBarHumedales
import com.example.humedalesdigitales.componentes.TopAppBarHumedales
import kotlinx.coroutines.delay

@Composable
fun Dormida(modifier: Modifier = Modifier, controladorNavegacion: NavController) {
    Scaffold(
        containerColor = Color(0xFF1A237E), // azul oscuro noche
        topBar = { TopAppBarHumedales("Humedales Digitales") },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = modifier.padding()) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "corazon",
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
                BarraDeVidaDormido()
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Cambia "rana" por tu imagen de rana dormida
            Image(
                painter = painterResource(id = R.drawable.dormida),
                contentDescription = "rana dormida",
                modifier = Modifier.fillMaxWidth(0.75f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = modifier.padding()) {
                // Luna → vuelve a Home (modo día)
                IconButton(onClick = {
                    controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Home.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.WbSunny, // sol para volver al día
                        contentDescription = "despertar",
                        tint = Color.Yellow,
                        modifier = Modifier.size(60.dp)
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(onClick = {
                    controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Juegos.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.SportsEsports,
                        contentDescription = "juegos",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                IconButton(onClick = {
                    controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Home.name)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Analytics,
                        contentDescription = "analytics",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BarraDeVidaDormido() {
    var vida by remember { mutableStateOf(1f) }
    LaunchedEffect(Unit) {
        while (vida > 0f) {
            delay(100)
            vida -= 0.01f
        }
    }
    LinearProgressIndicator(
        progress = vida,
        modifier = Modifier
            .padding()
            .height(30.dp),
        color = Color.Red,
        trackColor = Color.Gray
    )
}

@Composable
@Preview(showBackground = true)
fun prevdormido() {
    Dormida(controladorNavegacion = rememberNavController())
}