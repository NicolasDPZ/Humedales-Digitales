package com.example.humedalesdigitales.pantallas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
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

data class ItemCayendo(
    val id: Int,
    var x: Float,
    var y: Float,
    val emoji: String,
    val esMala: Boolean,
    val velocidad: Float
)

val basuras = listOf("🗑️", "🥤", "🧴", "🛍️")
val buenas  = listOf("🌿", "💧", "🌸", "🦆")

@Composable
fun AtrapaBasura(controladorNavegacion: NavController) {

    var ranaX by remember { mutableStateOf(0f) }
    var items by remember { mutableStateOf(listOf<ItemCayendo>()) }
    var score by remember { mutableStateOf(0) }
    var vidas by remember { mutableStateOf(3) }
    var corriendo by remember { mutableStateOf(false) }
    var juegoTerminado by remember { mutableStateOf(false) }
    var nextId by remember { mutableStateOf(0) }
    var frame by remember { mutableStateOf(0) }
    var anchoCanvas by remember { mutableStateOf(800f) }
    val alturaCanvas = 900f

    LaunchedEffect(corriendo) {
        if (!corriendo) return@LaunchedEffect
        while (corriendo && vidas > 0) {
            delay(16L)
            frame++
            val velocidadBase = 4f + score * 0.08f
            val intervalo = maxOf(30 - score / 5, 14)

            if (frame % intervalo == 0) {
                val esMala = (0..9).random() < 6
                val nuevoItem = ItemCayendo(
                    id = nextId++,
                    x = (40f..anchoCanvas - 40f).random(),
                    y = -40f,
                    emoji = if (esMala) basuras.random() else buenas.random(),
                    esMala = esMala,
                    velocidad = velocidadBase + (0f..2f).random()
                )
                items = items + nuevoItem
            }

            val nuevosItems = mutableListOf<ItemCayendo>()
            var perderVida = false
            var ganarPuntos = false

            for (item in items) {
                val itemActualizado = item.copy(y = item.y + item.velocidad)
                if (itemActualizado.y > alturaCanvas - 80f) {
                    val colision = kotlin.math.abs(itemActualizado.x - ranaX) < 60f
                    if (colision && item.esMala) ganarPuntos = true
                    else if (colision && !item.esMala) perderVida = true
                } else {
                    nuevosItems.add(itemActualizado)
                }
            }

            items = nuevosItems
            if (ganarPuntos) score += 2
            if (perderVida) vidas--
            if (vidas <= 0) { corriendo = false; juegoTerminado = true }
        }
    }

    fun reiniciar() {
        items = emptyList()
        score = 0
        vidas = 3
        juegoTerminado = false
        frame = 0
        corriendo = true
    }

    Scaffold(
        containerColor = Color(0xFFF0E97A),
        topBar = { TopAppBarHumedales("Atrapa la basura") },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                ) {
                    Text(
                        "Puntos: $score",
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
                ElevatedCard(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                ) {
                    Text(
                        "Vidas: ${"❤️".repeat(vidas)}",
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFB2EBF2), RoundedCornerShape(16.dp))
            ) {
                anchoCanvas = size.width
                if (ranaX == 0f) ranaX = size.width / 2f

                drawRect(
                    color = Color(0xFF4DD0E1),
                    topLeft = Offset(0f, size.height - 60f),
                    size = androidx.compose.ui.geometry.Size(size.width, 60f)
                )

                val paint = android.graphics.Paint().apply { textSize = 52f }
                items.forEach { item ->
                    drawContext.canvas.nativeCanvas.drawText(item.emoji, item.x - 26f, item.y, paint)
                }

                val ranaP = android.graphics.Paint().apply { textSize = 62f }
                drawContext.canvas.nativeCanvas.drawText("🐸", ranaX - 31f, size.height - 70f, ranaP)

                if (juegoTerminado) {
                    drawRect(color = Color(0x99000000))
                    val textP = android.graphics.Paint().apply {
                        color = android.graphics.Color.WHITE
                        textSize = 52f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                    drawContext.canvas.nativeCanvas.drawText(
                        "¡terminaste!", size.width / 2f, size.height / 2f - 30f, textP
                    )
                    textP.textSize = 38f
                    drawContext.canvas.nativeCanvas.drawText(
                        "puntos: $score", size.width / 2f, size.height / 2f + 20f, textP
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { ranaX = maxOf(40f, ranaX - anchoCanvas * 0.08f) },
                    modifier = Modifier.weight(1f).height(60.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    enabled = corriendo
                ) {
                    Text("◀", fontSize = 22.sp, color = Color(0xFF1A1A1A))
                }
                Button(
                    onClick = { ranaX = minOf(anchoCanvas - 40f, ranaX + anchoCanvas * 0.08f) },
                    modifier = Modifier.weight(1f).height(60.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    enabled = corriendo
                ) {
                    Text("▶", fontSize = 22.sp, color = Color(0xFF1A1A1A))
                }
            }

            Button(
                onClick = { reiniciar() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4DD0E1))
            ) {
                Text(
                    if (juegoTerminado || !corriendo) "Iniciar juego" else "Reiniciar",
                    color = Color(0xFF1A1A1A),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Button(
                onClick = { controladorNavegacion.popBackStack() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Volver atrás",
                    color = Color(0xFF1A1A1A),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

fun ClosedFloatingPointRange<Float>.random(): Float =
    start + (endInclusive - start) * kotlin.random.Random.nextFloat()

@Preview(showBackground = true)
@Composable
fun prevAtrapaBasura() {
    AtrapaBasura(controladorNavegacion = rememberNavController())
}