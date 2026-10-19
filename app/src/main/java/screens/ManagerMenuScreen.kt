@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mesago.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.R
import com.example.mesago.model.Dish
import com.example.mesago.model.SampleData
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

private val FondoPantalla = Color(0xFFF7F5F3)
private val VerdeOk = Color(0xFF2E7D32)
private val RosaBanner = Color(0xFFFCE4EC)

@Composable
fun ManagerMenuScreen(
    onAddDish: () -> Unit = {},
    onEditDish: (String) -> Unit = {},
    onSettings: () -> Unit = {},
    onStatisticsTab: () -> Unit = {},
    onProfileTab: () -> Unit = {}
) {
    val dishes = SampleData.managerDishes
    var navIndex by rememberSaveable { mutableIntStateOf(0) }

    val total = dishes.size
    val agotados = dishes.count { it.soldOut }
    val disponibles = total - agotados

    Scaffold(
        containerColor = FondoPantalla,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onSettings) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú", tint = MesaGoBlack)
                    }
                },
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.mesago_logo),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Menú", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MesaGoBlack)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificaciones", tint = MesaGoBlack)
                    }
                    Box(
                        Modifier
                            .padding(end = 12.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MesaGoRed.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil", tint = MesaGoRed)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoPantalla)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = MesaGoWhite) {
                NavigationBarItem(
                    selected = navIndex == 0,
                    onClick = { navIndex = 0 },
                    icon = { Icon(Icons.Default.Restaurant, contentDescription = "Menú") },
                    label = { Text("Menú", fontSize = 11.sp) },
                    colors = managerNavColors()
                )
                NavigationBarItem(
                    selected = navIndex == 1,
                    onClick = {
                        navIndex = 1
                        onStatisticsTab()
                    },
                    icon = { Icon(Icons.Default.BarChart, contentDescription = "Estadísticas") },
                    label = { Text("Estadísticas", fontSize = 11.sp) },
                    colors = managerNavColors()
                )
                NavigationBarItem(
                    selected = navIndex == 2,
                    onClick = {
                        navIndex = 2
                        onProfileTab()
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil", fontSize = 11.sp) },
                    colors = managerNavColors()
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = 720.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Store, contentDescription = null, tint = MesaGoGray, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "TASTY CENTRAL · PLANTA BAJA",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MesaGoGray,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                item {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Menú del día",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MesaGoBlack,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = onAddDish,
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MesaGoRed),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Agregar plato", fontSize = 13.sp)
                        }
                    }
                }

                item {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatChip("Total platos", total.toString(), MesaGoBlack)
                        StatChip("Disponibles", disponibles.toString(), MesaGoRed)
                        StatChip("Agotados", agotados.toString(), MesaGoRed)
                    }
                }

                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(RosaBanner)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("⚡", fontSize = 14.sp)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Los cambios en disponibilidad se reflejan de inmediato en la app estudiantil ULEAM.",
                            fontSize = 12.sp,
                            color = MesaGoBlack,
                            lineHeight = 16.sp
                        )
                    }
                }

                items(dishes, key = { it.id }) { dish ->
                    ManagerDishCard(
                        dish = dish,
                        onEdit = { onEditDish(dish.id) },
                        onToggleSoldOut = {
                            val i = dishes.indexOfFirst { it.id == dish.id }
                            if (i >= 0) dishes[i] = dish.copy(soldOut = !dish.soldOut)
                        },
                        onToggleVisibility = {
                            val i = dishes.indexOfFirst { it.id == dish.id }
                            if (i >= 0) dishes[i] = dish.copy(visibleInCatalog = !dish.visibleInCatalog)
                        },
                        onDelete = { dishes.remove(dish) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.StatChip(label: String, value: String, valueColor: Color) {
    Card(
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        border = BorderStroke(1.dp, MesaGoGray.copy(alpha = 0.12f))
    ) {
        Column(Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            Text(label, fontSize = 11.sp, color = MesaGoGray)
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = valueColor)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ManagerDishCard(
    dish: Dish,
    onEdit: () -> Unit,
    onToggleSoldOut: () -> Unit,
    onToggleVisibility: () -> Unit,
    onDelete: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val imageRes = imagenDePlato(dish.name)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Box(
                    Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MesaGoGray.copy(alpha = 0.15f))
                ) {
                    if (imageRes != null) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = dish.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            colorFilter = if (dish.soldOut) {
                                ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
                            } else null
                        )
                    } else {
                        Text("🍽", fontSize = 28.sp, modifier = Modifier.align(Alignment.Center))
                    }
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.Top) {
                        Text(
                            dish.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = if (dish.soldOut) MesaGoGray else MesaGoBlack,
                            modifier = Modifier.weight(1f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            "$%.2f".format(dish.price),
                            fontWeight = FontWeight.Bold,
                            color = if (dish.soldOut) MesaGoGray else MesaGoRed,
                            fontSize = 16.sp
                        )
                        Box {
                            IconButton(onClick = { menuExpanded = true }, modifier = Modifier.size(32.dp)) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Opciones", tint = MesaGoGray)
                            }
                            DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                                DropdownMenuItem(
                                    text = { Text("Eliminar plato") },
                                    onClick = {
                                        menuExpanded = false
                                        onDelete()
                                    }
                                )
                            }
                        }
                    }
                    Text(
                        dish.description,
                        fontSize = 12.sp,
                        color = MesaGoGray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(6.dp))
                    if (dish.soldOut) {
                        Text("Agotado", fontSize = 12.sp, color = MesaGoGray, fontWeight = FontWeight.Medium)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(VerdeOk)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text("Disponible", fontSize = 12.sp, color = VerdeOk)
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onEdit,
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, MesaGoGray.copy(alpha = 0.3f)),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(16.dp), tint = MesaGoBlack)
                    Spacer(Modifier.width(4.dp))
                    Text("Editar", fontSize = 12.sp, color = MesaGoBlack)
                }
                OutlinedButton(
                    onClick = onToggleSoldOut,
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, MesaGoRed.copy(alpha = 0.35f)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (dish.soldOut) MesaGoGray.copy(alpha = 0.08f) else MesaGoRed.copy(alpha = 0.08f)
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        if (dish.soldOut) Icons.Default.Check else Icons.Default.Block,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (dish.soldOut) MesaGoGray else MesaGoRed
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        if (dish.soldOut) "Marcar disponible" else "Marcar agotado",
                        fontSize = 12.sp,
                        color = if (dish.soldOut) MesaGoGray else MesaGoRed
                    )
                }
                IconButton(onClick = onToggleVisibility) {
                    Icon(
                        if (dish.visibleInCatalog) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Visibilidad en catálogo",
                        tint = if (dish.visibleInCatalog) MesaGoGray else MesaGoRed
                    )
                }
            }
        }
    }
}

@Composable
private fun managerNavColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = MesaGoRed,
    selectedTextColor = MesaGoRed,
    indicatorColor = MesaGoRed.copy(alpha = 0.12f),
    unselectedIconColor = MesaGoGray,
    unselectedTextColor = MesaGoGray
)

@Preview(showBackground = true, heightDp = 900)
@Composable
private fun PreviewManagerMenu() {
    MaterialTheme { ManagerMenuScreen() }
}
