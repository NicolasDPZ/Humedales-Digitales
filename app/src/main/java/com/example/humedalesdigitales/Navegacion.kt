package com.example.humedalesdigitales

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.pantallas.Home
import com.example.humedalesdigitales.pantallas.Juegos
import com.example.humedalesdigitales.pantallas.Mapa
import com.example.humedalesdigitales.pantallas.Misiones
import com.example.humedalesdigitales.pantallas.Perfil

@Composable
fun Navegacion() {
    val controladorNavegacion = rememberNavController()
    NavHost(
        navController = controladorNavegacion,
        startDestination = Pantalla.Home.name
    ) {
        composable(route = Pantalla.Home.name) {
            Home(controladorNavegacion = controladorNavegacion)
        }
        composable(route = Pantalla.Juegos.name) {
            Juegos(controladorNavegacion = controladorNavegacion)
        }
        composable(route = Pantalla.Juegos.name) {
            Mapa(controladorNavegacion = controladorNavegacion)
        }
        composable(route = Pantalla.Perfil.name) {
            Perfil(controladorNavegacion = controladorNavegacion)
        }
        composable(route = Pantalla.Misiones.name) {
            Misiones(controladorNavegacion = controladorNavegacion)
        }


    }
}

