package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
import kotlin.collections.map
import kotlin.collections.mapIndexed
import kotlin.collections.toMutableList


data class CartaJuego(
    val id: String,
    val emoji: String,
    val label: String,
    val index: Int
)

val itemsJuego = listOf(
    CartaJuego("rana",   "🐸", "rana",   0),
    CartaJuego("pato",   "🦆", "pato",   1),
    CartaJuego("planta", "🌿", "planta", 2),
    CartaJuego("agua",   "💧", "agua",   3),
    CartaJuego("pez",    "🐟", "pez",    4),
    CartaJuego("flor",   "🌸", "flor",   5),
)
@Composable
fun Parejas(modifier : Modifier = Modifier, controladorNavegacion: NavController){

    Scaffold(
        containerColor = (Color(0xFFF0E97A)),
        topBar = { TopAppBarHumedales("Parejas") },
    ) { paddingValues ->
        Column(

            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(text="Para este juego debes que encontrar la pareja pareja de cada ficha escogida, intenta hacerlo con la menor cantidad de intentos posibles, Buena suerte!!",
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 12.sp,
                    maxFontSize = 20.sp,
                    stepSize = 1.sp
                ),
                fontWeight = FontWeight.Bold,
                textAlign= TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(40.dp))
            JuegoPareja(controladorNavegacion)
        }
    }
}

@Composable
fun JuegoPareja( controladorNavegacion: NavController) {
    var cartas by remember { mutableStateOf(generarMazo()) }
    var voltedas by remember { mutableStateOf(listOf<Int>()) }
    var parejas by remember { mutableStateOf(0) }
    var movimientos by remember { mutableStateOf(0) }
    var bloqueado by remember { mutableStateOf(false) }

    LaunchedEffect(voltedas) {
        if (voltedas.size == 2) {
            bloqueado = true
            delay(1000)
            val (a, b) = voltedas
            if (cartas[a].id == cartas[b].id) {
                cartas = cartas.mapIndexed { i, c ->
                    if (i == a || i == b) c.copy(label = c.label) else c
                }
                val nuevasParejasIds = setOf(cartas[a].id)
                cartas = cartas.map { c ->
                    if (c.id in nuevasParejasIds && (cartas.indexOf(c) == a || cartas.indexOf(c) == b))
                        c else c
                }
                parejas++
                val aId = cartas[a].id
                val bId = cartas[b].id
                cartas = cartas.toMutableList().also { lista ->
                    lista[a] = lista[a].copy(index = -1)
                    lista[b] = lista[b].copy(index = -1)
                }
            }
            voltedas = emptyList()
            movimientos++
            bloqueado = false
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf(
            "$movimientos movimientos",
            "$parejas/6 parejas"
        ).forEach { txt ->
            ElevatedCard(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                elevation = CardDefaults.elevatedCardElevation(2.dp)
            ) {
                Text(
                    text = txt,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(30.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().padding(12.dp)
    ) {
        items(cartas.size) { i ->
            val carta = cartas[i]
            val estaVolteada = i in voltedas
            val estaEmparejada = carta.index == -1

            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(
                        color = when {
                            estaEmparejada -> Color(0xFF81C784)
                            estaVolteada -> Color.White
                            else -> Color(0xFF4DD0E1)
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(enabled = !bloqueado && !estaVolteada && !estaEmparejada) {
                        if (voltedas.size < 2) {
                            voltedas = voltedas + i
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                if (estaVolteada || estaEmparejada) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(carta.emoji, fontSize = 30.sp)
                        Text(carta.label, fontSize = 12.sp, color = Color(0xFF444444))
                    }
                }
            }
        }
    }

    if (parejas == 6) {
        Text(
            text = "¡ganaste en $movimientos movimientos!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2E7D32)
        )
    }

    Spacer(modifier = Modifier.height(30.dp))

    Button(
        onClick = {
            cartas = generarMazo()
            voltedas = emptyList()
            parejas = 0
            movimientos = 0
            bloqueado = false
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4DD0E1)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text("Nueva partida", color = Color(0xFF1A1A1A), fontWeight = FontWeight.Medium)
    }


    Button(
        onClick = {
            controladorNavegacion.navigate(route = com.example.humedalesdigitales.Pantalla.Juegos.name)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4DD0E1)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text("Volver atras", color = Color(0xFF1A1A1A), fontWeight = FontWeight.Medium)
    }
}

fun generarMazo(): List<CartaJuego> {
    return (itemsJuego + itemsJuego).shuffled().mapIndexed { i, c -> c.copy(index = i) }
}


@Composable
@Preview(showBackground = true)
fun prevParejas(){
    Parejas(controladorNavegacion = rememberNavController())
}



