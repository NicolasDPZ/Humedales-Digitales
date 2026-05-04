package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.componentes.BotAppBarHumedales
import com.example.humedalesdigitales.componentes.TopAppBarHumedales

data class Mision(
    val titulo: String,
    val descripcion: String,
    var completada: Boolean = false
)

@Composable
fun Misiones(controladorNavegacion: NavController) {

    var misiones by remember {
        mutableStateOf(
            listOf(
                Mision("Recicla en casa",         "Separa residuos durante una semana"),
                Mision("Ahorra agua",             "Cierra el grifo mientras te cepillas los dientes 5 días"),
                Mision("Visita un humedal",       "Ve a un humedal cercano y observa la naturaleza"),
                Mision("Planta algo",             "Siembra una planta nativa en tu hogar o jardín"),
                Mision("Usa bicicleta",           "Reemplaza un viaje en carro por bicicleta"),
                Mision("Limpieza de zona",        "Recoge basura en un espacio público cerca de ti"),
                Mision("Aprende una especie",     "Investiga sobre un animal del humedal bogotano"),
                Mision("Comparte el mensaje",     "Cuéntale a alguien sobre la importancia de los humedales"),
                Mision("Reduce el plástico",      "Usa bolsa reutilizable en tus compras esta semana"),
                Mision("Fotografía la naturaleza","Toma una foto de un animal o planta silvestre"),
            )
        )
    }

    val completadas = misiones.count { it.completada }
    val progreso = completadas.toFloat() / misiones.size

    Scaffold(
        containerColor = Color(0xFFF0E97A),
        topBar = { TopAppBarHumedales("misiones") },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Progreso general
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "progreso: $completadas/${misiones.size}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF2E7D32)
                    )
                    LinearProgressIndicator(
                        progress = progreso,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .padding(0.dp),
                        color = Color(0xFF4DD0E1),
                        trackColor = Color(0xFFE0E0E0)
                    )
                }
            }

            // Lista misiones
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(misiones) { mision ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = if (mision.completada) Color(0xFFE8F5E9) else Color.White
                        ),
                        elevation = CardDefaults.elevatedCardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    misiones = misiones.map {
                                        if (it.titulo == mision.titulo)
                                            it.copy(completada = !it.completada)
                                        else it
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (mision.completada)
                                        Icons.Filled.CheckCircle
                                    else
                                        Icons.Filled.RadioButtonUnchecked,
                                    contentDescription = "completar",
                                    tint = if (mision.completada) Color(0xFF2E7D32) else Color(0xFFB0BEC5),
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = mision.titulo,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = if (mision.completada) Color(0xFF2E7D32) else Color(0xFF1A1A1A)
                                )
                                Text(
                                    text = mision.descripcion,
                                    fontSize = 11.sp,
                                    color = Color(0xFF757575),
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
fun prevMisiones() {
    Misiones(controladorNavegacion = rememberNavController())
}