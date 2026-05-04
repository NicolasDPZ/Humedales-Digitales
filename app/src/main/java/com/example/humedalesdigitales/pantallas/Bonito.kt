package com.example.humedalesdigitales.pantallas

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.humedalesdigitales.R
import com.example.humedalesdigitales.componentes.BotAppBarHumedales
import com.example.humedalesdigitales.componentes.TopAppBarHumedales
import java.io.File

data class Especie(
    val nombre: String,
    val descripcion: String,
    val drawable: Int?,
    val revelada: Boolean = false
)

@Composable
fun Bonito(controladorNavegacion: NavController) {

    val context = LocalContext.current

    var especies by remember {
        mutableStateOf(
            listOf(
                Especie("Tingua Azul",     "Ave acuática de plumaje azul oscuro",       R.drawable.tinguaazul,      revelada = true),
                Especie("Tingua Bogotana", "Especie endémica de los humedales bogotanos", R.drawable.tinguabogotana, revelada = true),
                Especie("Pato",            "Pato común de los humedales",               R.drawable.pato,            revelada = true),
                Especie("Búho",            "Ave nocturna guardiana del humedal",         R.drawable.buho,            revelada = true),
                Especie("Curí",            "Roedor nativo de los humedales andinos",     R.drawable.curi,            revelada = true),
                Especie("Rana Sabanera",   "Anfibio indicador de la salud del humedal", R.drawable.ranasabanera,    revelada = true),
                Especie("???",             "",                                           null,                       revelada = false),
                Especie("???",             "",                                           null,                       revelada = false),
                Especie("???",             "",                                           null,                       revelada = false),
                Especie("???",             "",                                           null,                       revelada = false),
                Especie("???",             "",                                           null,                       revelada = false),
                Especie("???",             "",                                           null,                       revelada = false),
            ).shuffled()
        )
    }

    var especieSeleccionada by remember { mutableStateOf<Especie?>(null) }

    var fotoUri by remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { exito ->
        if (exito) {
        }
    }

    fun abrirCamara() {
        val fotoFile = File(context.cacheDir, "foto_especie_${System.currentTimeMillis()}.jpg")
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            fotoFile
        )
        fotoUri = uri
        cameraLauncher.launch(uri)
    }

    especieSeleccionada?.let { especie ->
        if (especie.revelada) {
            AlertDialog(
                onDismissRequest = { especieSeleccionada = null },
                containerColor = Color.White,
                shape = RoundedCornerShape(20.dp),
                title = {
                    Text(
                        especie.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        especie.drawable?.let {
                            Image(
                                painter = painterResource(id = it),
                                contentDescription = especie.nombre,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(14.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(
                            especie.descripcion,
                            fontSize = 14.sp,
                            color = Color(0xFF555555),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { especieSeleccionada = null }) {
                        Text("cerrar", color = Color(0xFF2E7D32))
                    }
                }
            )
        }
    }

    Scaffold(
        containerColor = Color(0xFFF0E97A),
        topBar = { TopAppBarHumedales("especies") },
        bottomBar = { BotAppBarHumedales(controladorNavegacion) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val reveladas = especies.count { it.revelada }
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "especies encontradas: $reveladas/${especies.size}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF2E7D32)
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(especies.size) { i ->
                        val especie = especies[i]
                        ElevatedCard(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clickable { especieSeleccionada = especie },
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.elevatedCardColors(
                                containerColor = if (especie.revelada) Color.White else Color(0xFF1A1A1A)
                            ),
                            elevation = CardDefaults.elevatedCardElevation(3.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                if (especie.revelada && especie.drawable != null) {
                                    Image(
                                        painter = painterResource(id = especie.drawable),
                                        contentDescription = especie.nombre,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0x88000000))
                                            .align(Alignment.BottomCenter)
                                            .padding(4.dp)
                                    ) {
                                        Text(
                                            text = especie.nombre,
                                            fontSize = 9.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Medium,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                } else {
                                    Text(
                                        text = "?",
                                        fontSize = 32.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            FloatingActionButton(
                onClick = { abrirCamara() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                containerColor = Color(0xFF2E7D32),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "tomar foto",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevEspecies() {
    Bonito(controladorNavegacion = rememberNavController())
}