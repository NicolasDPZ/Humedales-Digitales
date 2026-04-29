package com.example.humedalesdigitales.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BotAppBarHumedales(controladorNavegacion : NavController){
    BottomAppBar(
        containerColor =  Color(0xFF88AC4C),
        tonalElevation = 0.dp,
        actions = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                ) {

                IconButton(onClick = {controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Mapa.name)} ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "mapita",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Crear.name)}) {
                    Icon(
                        imageVector = Icons.Filled.AddTask,
                        contentDescription = "crear",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Home.name)}) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "home",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = { controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Mensajes.name)}) {
                    Icon(
                        imageVector = Icons.Filled.AutoAwesome,
                        contentDescription = "mensajes",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {controladorNavegacion.navigate(route= com.example.humedalesdigitales.Pantalla.Perfil.name)}) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "perfil",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    )
}