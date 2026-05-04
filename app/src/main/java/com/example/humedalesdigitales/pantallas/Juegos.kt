package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.componentes.BotAppBarHumedales
import com.example.humedalesdigitales.componentes.TopAppBarHumedales

data class JuegoItem(
    val nombre: String,
    val descripcion: String,
    val icono: ImageVector,
    val ruta: String?,
    val color: Color
)

@Composable
fun Juegos(modifier: Modifier = Modifier, controladorNavegacion: NavController) {

    val juegos = listOf(
        JuegoItem(
            nombre = "Parejas",
            descripcion = "Une los elementos de los humedales",
            icono = Icons.Filled.Pets,
            ruta = com.example.humedalesdigitales.Pantalla.Parejas.name,
            color = Color(0xFF4DD0E1)
        ),
        JuegoItem(
            nombre = "Atrapa la basura",
            descripcion = "Mueve la rana, atrapa lo malo no dejes caer basura",
            icono = Icons.Filled.RestoreFromTrash,
            ruta = com.example.humedalesdigitales.Pantalla.AtrapaBasura.name,
            color = Color(0xFF178DCE)
        ),
        JuegoItem(
            nombre = "Próximamente...",
            descripcion = "nuevo juego en camino",
            icono = Icons.Filled.Lock,
            ruta = null,
            color = Color(0xFFB0BEC5)
        ),
        JuegoItem(
            nombre = "Próximamente...",
            descripcion = "nuevo juego en camino",
            icono = Icons.Filled.Lock,
            ruta = null,
            color = Color(0xFFB0BEC5)
        ),
        JuegoItem(
            nombre = "Próximamente...",
            descripcion = "nuevo juego en camino",
            icono = Icons.Filled.Lock,
            ruta = null,
            color = Color(0xFFB0BEC5)
        ),
    )

    Scaffold(
        containerColor = Color(0xFFF0E97A),
        topBar = { TopAppBarHumedales("juegos") },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(){
                IconButton(onClick = { controladorNavegacion.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "volver",
                        tint = Color(0xFF1A1A1A)
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "selecciona un juego",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A),
                    textAlign = TextAlign.Center
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(juegos.size) { i ->
                    val juego = juegos[i]
                    ElevatedCard(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable(enabled = juego.ruta != null) {
                                juego.ruta?.let { controladorNavegacion.navigate(it) }
                            },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.elevatedCardElevation(
                            defaultElevation = if (juego.ruta != null) 4.dp else 1.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(
                                        color = juego.color.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = juego.icono,
                                    contentDescription = juego.nombre,
                                    tint = juego.color,
                                    modifier = Modifier.size(40.dp)
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = juego.nombre,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (juego.ruta != null) Color(0xFF1A1A1A) else Color(0xFF9E9E9E)
                                )
                                Text(
                                    text = juego.descripcion,
                                    fontSize = 11.sp,
                                    color = Color(0xFF9E9E9E),
                                    lineHeight = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevJuegosSeleccion() {
    Juegos(controladorNavegacion = rememberNavController())
}