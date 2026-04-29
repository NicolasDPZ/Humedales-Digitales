package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.componentes.BotAppBarHumedales
import com.example.humedalesdigitales.componentes.TopAppBarHumedales

@Composable
fun Bonito(modifier : Modifier = Modifier, controladorNavegacion: NavController) {
    Scaffold(
        containerColor = (Color(0xFFF0E97A)),
        topBar = { TopAppBarHumedales(controladorNavegacion) },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->
        Column(

            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {}
    }
}


@Composable
@Preview(showBackground = true)
fun prevbonito(){
    Bonito(controladorNavegacion = rememberNavController())
}
