@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mesago.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.model.Plato
import com.example.mesago.model.platosDemo
import com.example.mesago.model.tastysDemo
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

private val VerdeOk = Color(0xFF2E7D32)
private val VerdeSuave = Color(0xFFE3F5E8)
private val FondoPantalla = Color(0xFFF7F5F3)

// Menú de un Tasty: pestañas, lista de platos con disponibilidad y precio.
// onPlato recibe la posición del plato en la lista original (platosDemo).
@Composable
fun MenuTastyScreen(
    tastyId: Int = 0,
    onPlato: (Int) -> Unit = {},
    onVolver: () -> Unit = {}
) {
    val tasty = tastysDemo.getOrNull(tastyId)
    var tab by remember { mutableStateOf(0) }
    var favorito by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = FondoPantalla,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = FondoPantalla),
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            tasty?.nombre ?: "Menú",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (tasty != null) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Filled.LocationOn, null,
                                    tint = MesaGoRed, modifier = Modifier.size(12.dp)
                                )
                                Text(tasty.ubicacion, color = MesaGoGray, fontSize = 12.sp)
                            }
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { favorito = !favorito }) {
                        Icon(
                            if (favorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (favorito) MesaGoRed else MesaGoBlack
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            Pestanas(tab) { tab = it }

            if (tab == 0) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.size(7.dp).clip(CircleShape).background(Color(0xFF26A69A)))
                    Spacer(Modifier.width(6.dp))
                    Text("Menú disponible hoy", fontSize = 12.sp, color = MesaGoGray)
                    Spacer(Modifier.weight(1f))
                    Text("Actualizado 07:00 AM", fontSize = 11.sp, color = MesaGoGray)
                }
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(platosDemo) { index, plato ->
                        PlatoItem(plato, onClick = { onPlato(index) })
                    }
                    item {
                        Text(
                            "Consulta de platos en tiempo real · Campus Universitario ULEAM",
                            color = MesaGoGray,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            } else {
                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("Ubicación", fontWeight = FontWeight.Bold)
                    Text(tasty?.ubicacion ?: "-", color = MesaGoGray)
                    Spacer(Modifier.height(12.dp))
                    Text("Estado", fontWeight = FontWeight.Bold)
                    Text(
                        if (tasty?.abierto == true) "Abierto" else "Cerrado",
                        color = if (tasty?.abierto == true) VerdeOk else MesaGoRed
                    )
                }
            }
        }
    }
}

@Composable
private fun Pestanas(seleccion: Int, onSelect: (Int) -> Unit) {
    val titulos = listOf("Menú del día", "Información")
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFEDEAE7))
            .padding(3.dp)
    ) {
        titulos.forEachIndexed { i, titulo ->
            val activa = i == seleccion
            Box(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (activa) MesaGoWhite else Color.Transparent)
                    .then(
                        if (activa) Modifier.border(
                            BorderStroke(1.dp, MesaGoRed.copy(alpha = 0.35f)),
                            RoundedCornerShape(10.dp)
                        ) else Modifier
                    )
                    .clickable { onSelect(i) }
                    .padding(vertical = 9.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    titulo,
                    fontSize = 13.sp,
                    fontWeight = if (activa) FontWeight.Bold else FontWeight.Normal,
                    color = if (activa) MesaGoRed else MesaGoGray
                )
            }
        }
    }
}

@Composable
private fun PlatoItem(plato: Plato, onClick: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(Modifier.padding(12.dp)) {
            val img = imagenDePlato(plato.nombre)
            if (img != null) {
                Image(
                    painter = painterResource(img),
                    contentDescription = plato.nombre,
                    contentScale = ContentScale.Crop,
                    colorFilter = if (plato.agotado)
                        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                    else null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            } else {
                Box(
                    Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (plato.agotado) Color(0xFFE0E0E0)
                            else MesaGoRed.copy(alpha = 0.12f)
                        ),
                    contentAlignment = Alignment.Center
                ) { Text("🍽", fontSize = 28.sp) }
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Top) {
                    Text(
                        plato.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (plato.agotado) MesaGoGray else MesaGoBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        "\$%.2f".format(plato.precio),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (plato.agotado) MesaGoGray.copy(alpha = 0.6f) else MesaGoBlack
                    )
                }
                Text(
                    plato.descripcion,
                    color = MesaGoGray,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (plato.agotado) {
                        Etiqueta("Agotado", fondo = Color(0xFF424242), colorTexto = Color.White)
                    } else {
                        Etiqueta("Disponible", fondo = VerdeSuave, colorTexto = VerdeOk)
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        if (plato.agotado) "Detalles" else "Ver detalle",
                        color = if (plato.agotado) MesaGoGray else MesaGoRed,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
                        tint = if (plato.agotado) MesaGoGray else MesaGoRed,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Etiqueta(texto: String, fondo: Color, colorTexto: Color) {
    Box(
        Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(fondo)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(texto, color = colorTexto, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuTasty() {
    MaterialTheme { MenuTastyScreen() }
}