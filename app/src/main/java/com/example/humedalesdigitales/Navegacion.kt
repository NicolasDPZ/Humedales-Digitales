package com.example.humedalesdigitales

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.pantallas.Home

@Composable
fun Navegacion(){
    val controladorNavegacion = rememberNavController()
    NavHost(
        navController = controladorNavegacion,
        startDestination = pantallas.Home.name
    )
    {
        composable(route = pantallas.Home.name){
            Home(controladorNavegacion = controladorNavegacion)
        }

        composable(route = pantallas.Juegos.name){
            Juegos(controladorNavegacion = controladorNavegacion)
        }


    }
}

