@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.example.mesago.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.model.platosDemo
import com.example.mesago.model.tastysDemo
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

private val Verde = Color(0xFF2E7D32)
private val VerdeSuave = Color(0xFFE3F5E8)
private val Fondo = Color(0xFFF7F5F3)

// Detalle de un plato: imagen, nombre, precio, descripción, disponibilidad y ubicación.
@Composable
fun DetallePlatoScreen(
    platoId: Int = 0,
    tastyId: Int = 0,
    onVolver: () -> Unit = {}
) {
    val plato = platosDemo.getOrNull(platoId)
    val tasty = tastysDemo.getOrNull(tastyId)
    var favorito by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Fondo,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Fondo),
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "MESAGO · CAMPUS",
                            color = MesaGoRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp
                        )
                        Text("Detalle del plato", fontSize = 14.sp, fontWeight = FontWeight.Medium)
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
                            tint = if (favorito) MesaGoRed else MesaGoGray
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
                Button(
                    onClick = onVolver,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MesaGoBlack,
                        contentColor = Color.White
                    )
                ) {
                    Text("↩  Volver al menú", fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { padding ->
        if (plato == null) {
            Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Plato no encontrado")
            }
        } else {
            Column(
                Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Imagen (placeholder) con etiquetas superpuestas
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MesaGoRed.copy(alpha = 0.12f))
                ) {
                    val img = imagenDetalleDePlato(plato.nombre)
                    if (img != null) {
                        Image(
                            painter = painterResource(img),
                            contentDescription = plato.nombre,
                            contentScale = ContentScale.Crop,
                            colorFilter = if (plato.agotado)
                                ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                            else null,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Text("🍽", fontSize = 72.sp, modifier = Modifier.align(Alignment.Center))
                    }

                    Row(
                        Modifier
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(MesaGoWhite)
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.size(7.dp).clip(CircleShape).background(MesaGoRed))
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "${tasty?.nombre ?: "Tasty"} · ${tasty?.ubicacion ?: ""}".trim(' ', '·'),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        plato.categoria,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black.copy(alpha = 0.6f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                // Tarjeta principal
                Card(
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                plato.nombre,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                "\$%.2f".format(plato.precio),
                                fontSize = 24.sp,
                                color = MesaGoRed,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(plato.descripcion, color = MesaGoGray, fontSize = 14.sp, lineHeight = 21.sp)
                        Spacer(Modifier.height(14.dp))
                        HorizontalDivider(color = Color(0xFFEDEAE7))
                        Spacer(Modifier.height(14.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                if (plato.agotado) Icons.Filled.Warning else Icons.Filled.CheckCircle,
                                contentDescription = null,
                                tint = if (plato.agotado) MesaGoRed else Verde,
                                modifier = Modifier.size(26.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Column(Modifier.weight(1f)) {
                                Text(
                                    if (plato.agotado) "No disponible hoy" else "Disponible hoy",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = if (plato.agotado) MesaGoRed else MesaGoBlack
                                )
                                Text(
                                    if (plato.agotado) "Stock agotado por hoy"
                                    else "Servido de 11:30 a 15:00 o hasta agotar stock",
                                    color = MesaGoGray,
                                    fontSize = 11.sp
                                )
                            }
                            if (!plato.agotado) {
                                Text(
                                    "En barra",
                                    color = Verde,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(VerdeSuave)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(Modifier.height(14.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("✓ Ingredientes frescos", "Cocción diaria", "Opción saludable")
                                .forEach { Etiqueta(it) }
                        }
                    }
                }

                // Tarjeta del Tasty
                if (tasty != null) {
                    Card(
                        Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text(
                                    tasty.nombre.uppercase(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Text(tasty.ubicacion, color = MesaGoGray, fontSize = 12.sp)
                            }
                            Text(
                                "ULEAM",
                                color = MesaGoRed,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MesaGoRed.copy(alpha = 0.1f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun Etiqueta(texto: String) {
    Text(
        texto,
        fontSize = 11.sp,
        color = MesaGoBlack,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF1EFED))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetallePlato() {
    MaterialTheme { DetallePlatoScreen() }
}