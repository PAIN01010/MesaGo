@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mesago.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.model.Tasty
import com.example.mesago.model.tastysDemo
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite
import androidx.compose.runtime.saveable.rememberSaveable

private val VerdeAbierto = Color(0xFF2E7D32)
private val FondoVerde = Color(0xFFE6F4EA)

// Pantalla principal del estudiante (prototipo: "Tasty Food disponibles").
// onTasty recibe la posición del Tasty en la lista (luego será el id de Room).
@Composable
fun HomeScreen(
    onTasty: (Int) -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    var busqueda by rememberSaveable { mutableStateOf("") }
    val visibles = tastysDemo.withIndex().filter {
        it.value.nombre.contains(busqueda, ignoreCase = true) ||
                it.value.ubicacion.contains(busqueda, ignoreCase = true)
    }
    val activos = tastysDemo.count { it.abierto }

    Scaffold(
        containerColor = MesaGoWhite,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Configuración", tint = MesaGoBlack)
                    }
                },
                title = {
                    Column {
                        Text("MesaGo", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MesaGoBlack)
                        Text("ULEAM · Inicio", fontSize = 12.sp, color = MesaGoGray)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificaciones", tint = MesaGoBlack)
                    }
                    IconButton(
                        onClick = onProfileClick,
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Box(
                            Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(MesaGoRed.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = "Perfil", tint = MesaGoRed)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MesaGoWhite)
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                OutlinedTextField(
                    value = busqueda,
                    onValueChange = { busqueda = it },
                    placeholder = { Text("Buscar Tasty por facultad o edificio...", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = MesaGoGray) },
                    singleLine = true,
                    shape = RoundedCornerShape(28.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MesaGoBlack,
                        unfocusedTextColor = MesaGoBlack,
                        cursorColor = MesaGoRed,
                        focusedBorderColor = MesaGoRed,
                        unfocusedBorderColor = MesaGoGray.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Tasty Food disponibles",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MesaGoBlack,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "● $activos activos",
                            color = VerdeAbierto,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(FondoVerde)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Consulta el menú del día disponible en cada cafetería universitaria.",
                        color = MesaGoGray,
                        fontSize = 14.sp
                    )
                }
            }
            items(visibles) { item ->
                TastyCard(item.value, onVerMenu = { onTasty(item.index) })
            }
            item { AvisoHorario() }
        }
    }
}

@Composable
private fun TastyCard(tasty: Tasty, onVerMenu: () -> Unit) {
    Card(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row {
                // Espacio de la foto: luego se reemplaza por Image(painterResource(...))
                Box(
                    Modifier
                        .size(84.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MesaGoGray.copy(alpha = 0.25f))
                ) {
                    Text("🍴", fontSize = 28.sp, modifier = Modifier.align(Alignment.Center))
                    Text(
                        tasty.sigla,
                        color = MesaGoWhite,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MesaGoRed)
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            tasty.nombre,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = MesaGoBlack,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            if (tasty.abierto) "Abierto" else "Cerrado",
                            color = if (tasty.abierto) VerdeAbierto else MesaGoRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(if (tasty.abierto) FondoVerde else MesaGoRed.copy(alpha = 0.12f))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            Icons.Default.LocationOn, null,
                            tint = MesaGoRed,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(tasty.ubicacion, color = MesaGoGray, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                    Spacer(Modifier.height(2.dp))
                    Text("🕒 ${tasty.horario}", color = MesaGoGray, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "🍴 ${tasty.descripcion}",
                    color = MesaGoGray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Button(
                    onClick = onVerMenu,
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MesaGoRed)
                ) {
                    Text("Ver menú", color = MesaGoWhite, fontWeight = FontWeight.SemiBold)
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = MesaGoWhite)
                }
            }
        }
    }
}

@Composable
private fun AvisoHorario() {
    Card(
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoGray.copy(alpha = 0.1f))
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Info, contentDescription = null, tint = MesaGoRed)
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Horario de servicio ULEAM", fontWeight = FontWeight.Bold, color = MesaGoBlack, fontSize = 14.sp)
                Text(
                    "Los menús se actualizan a diario a las 07:00. Acércate directamente a tu punto asignado.",
                    color = MesaGoGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    MaterialTheme { HomeScreen() }
}