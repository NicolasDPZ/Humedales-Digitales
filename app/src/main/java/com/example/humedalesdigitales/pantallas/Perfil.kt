package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.componentes.BotAppBarHumedales
import com.example.humedalesdigitales.componentes.TopAppBarHumedales

@Composable
fun Perfil(modifier: Modifier = Modifier, controladorNavegacion: NavController) {

    val configuration = LocalConfiguration.current
    val imagenSize = (configuration.screenWidthDp * 0.30f).dp

    Scaffold(
        containerColor = Color(0xFFF0E97A),
        topBar = { TopAppBarHumedales("Juan Erasmo") },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(30.dp))


            // --- Hero: rana + porcentaje ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = com.example.humedalesdigitales.R.drawable.rana),
                    contentDescription = "mascota",
                    modifier = Modifier.size(imagenSize),
                    contentScale = ContentScale.Fit
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "you have helped",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFF4DD0E1),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 24.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "51%",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF004D5C)
                        )
                    }
                    Text(
                        text = "to take care of the wetlands",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))


            // --- Gráfica de barras ---
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "actividades",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color(0xFF555555)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    val actividades = listOf(
                        "recycle" to 10f,
                        "save water" to 8f,
                        "bicycle" to 12f,
                        "plants" to 5f
                    )
                    val maxVal = actividades.maxOf { it.second }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        actividades.forEach { (nombre, valor) ->
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(valor / maxVal)
                                        .background(
                                            color = Color(0xFF4DD0E1),
                                            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
                                        )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(nombre, fontSize = 10.sp, color = Color(0xFF444444))
                            }
                        }
                    }
                }
            }

            // --- Stats ---
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("24" to "misiones completadas", "7" to "días seguidos").forEach { (num, lbl) ->
                    ElevatedCard(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(num, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            Text(lbl, fontSize = 13.sp, color = Color(0xFF666666))
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun prevperfil() {
    Perfil(controladorNavegacion = rememberNavController())
}