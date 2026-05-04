package com.example.humedalesdigitales.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.humedalesdigitales.Pantalla
import com.example.humedalesdigitales.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHumedales(titulo: String) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titulo,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        },

        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "imagen logo",
                modifier = Modifier.size(56.dp),
            )
        },

        actions = {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "opciones",
            )},

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF88AC4C)
        )
    )
}