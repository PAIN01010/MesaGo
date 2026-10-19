@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mesago.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

private val FondoPantalla = Color(0xFFF7F5F3)
private val RosaSuave = Color(0xFFFCE4EC)

@Composable
fun SettingsScreen(
    isManager: Boolean = false,
    onBack: () -> Unit = {},
    onLogout: () -> Unit = {},
    onChangeRole: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onHomeTab: () -> Unit = {},
    onTastyTab: () -> Unit = {},
    onProfileTab: () -> Unit = {}
) {
    var modoOscuro by rememberSaveable { mutableStateOf(false) }
    var mantenerSesion by rememberSaveable { mutableStateOf(true) }
    var notificaciones by rememberSaveable { mutableStateOf(true) }
    var navIndex by rememberSaveable { mutableIntStateOf(2) }

    Scaffold(
        containerColor = FondoPantalla,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú", tint = MesaGoBlack)
                    }
                },
                title = {
                    Column {
                        Text("MesaGo", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = MesaGoRed)
                        Text(
                            if (isManager) "ULEAM · Encargado" else "ULEAM · Inicio",
                            fontSize = 12.sp,
                            color = MesaGoGray
                        )
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
                if (isManager) {
                    // Tabs del encargado
                    NavigationBarItem(
                        selected = navIndex == 0,
                        onClick = {
                            navIndex = 0
                            onHomeTab()
                        },
                        icon = { Icon(Icons.Default.Restaurant, contentDescription = "Menú") },
                        label = { Text("Menú", fontSize = 11.sp) },
                        colors = navColors()
                    )
                    NavigationBarItem(
                        selected = navIndex == 1,
                        onClick = {
                            navIndex = 1
                            onTastyTab()
                        },
                        icon = { Icon(Icons.Default.BarChart, contentDescription = "Estadísticas") },
                        label = { Text("Estadísticas", fontSize = 11.sp) },
                        colors = navColors()
                    )
                } else {
                    // Tabs del estudiante
                    NavigationBarItem(
                        selected = navIndex == 0,
                        onClick = {
                            navIndex = 0
                            onHomeTab()
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio", fontSize = 11.sp) },
                        colors = navColors()
                    )
                    NavigationBarItem(
                        selected = navIndex == 1,
                        onClick = {
                            navIndex = 1
                            onTastyTab()
                        },
                        icon = { Icon(Icons.Default.Restaurant, contentDescription = "Tasty") },
                        label = { Text("Tasty", fontSize = 11.sp) },
                        colors = navColors()
                    )
                }
                NavigationBarItem(
                    selected = navIndex == 2,
                    onClick = {
                        navIndex = 2
                        onProfileTab()
                    },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil", fontSize = 11.sp) },
                    colors = navColors()
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
            Column(
                modifier = Modifier
                    .widthIn(max = 640.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MesaGoBlack
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Configuración",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = MesaGoBlack
                        )
                        Text("Preferencias y sesión local", fontSize = 13.sp, color = MesaGoGray)
                    }
                    Text(
                        "✔ ULEAM",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MesaGoRed,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(RosaSuave)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                ProfileCard(isManager = isManager)

                Spacer(Modifier.height(20.dp))

                SectionHeader("PREFERENCIAS DE APLICACIÓN", trailing = "Persistencia local")
                SettingsCard {
                    TogglePreferenceRow(
                        icon = Icons.Default.DarkMode,
                        title = "Modo oscuro",
                        subtitle = "Ajustar apariencia visual de la interfaz",
                        checked = modoOscuro,
                        onCheckedChange = { modoOscuro = it }
                    )
                    HorizontalDivider(color = MesaGoGray.copy(alpha = 0.15f))
                    TogglePreferenceRow(
                        icon = Icons.Default.Lock,
                        title = "Mantener sesión iniciada",
                        subtitle = "Guardar credenciales localmente",
                        checked = mantenerSesion,
                        onCheckedChange = { mantenerSesion = it }
                    )
                    HorizontalDivider(color = MesaGoGray.copy(alpha = 0.15f))
                    TogglePreferenceRow(
                        icon = Icons.Default.Notifications,
                        title = "Notificaciones de menús",
                        subtitle = "Avisos de apertura y platos en Tasty Food",
                        checked = notificaciones,
                        onCheckedChange = { notificaciones = it }
                    )
                    HorizontalDivider(color = MesaGoGray.copy(alpha = 0.15f))
                    LinkPreferenceRow(
                        icon = Icons.Default.SwapHoriz,
                        title = "Cambiar rol de acceso",
                        subtitle = "Volver a estudiante o encargado del comedor",
                        onClick = onChangeRole
                    )
                }

                Spacer(Modifier.height(20.dp))

                SectionHeader("INFORMACIÓN Y AYUDA")
                SettingsCard {
                    LinkPreferenceRow(
                        icon = Icons.Default.Description,
                        title = "Términos y condiciones",
                        subtitle = "Políticas de uso y privacidad universitaria",
                        onClick = onTermsClick
                    )
                    HorizontalDivider(color = MesaGoGray.copy(alpha = 0.15f))
                    LinkPreferenceRow(
                        icon = Icons.Default.Info,
                        title = "Acerca de MesaGo",
                        subtitle = "Tasty Food · Campus Matriz ULEAM",
                        badge = "v1.0.0",
                        onClick = onAboutClick
                    )
                }

                Spacer(Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onLogout),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Row(
                        Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(MesaGoRed.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Outlined.Logout, contentDescription = null, tint = MesaGoRed)
                        }
                        Spacer(Modifier.width(14.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Cerrar sesión", fontWeight = FontWeight.SemiBold, color = MesaGoRed, fontSize = 16.sp)
                            Text(
                                "Finalizar sesión segura en este dispositivo",
                                fontSize = 12.sp,
                                color = MesaGoGray
                            )
                        }
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MesaGoGray
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ProfileCard(isManager: Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(MesaGoGray.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("CM", fontWeight = FontWeight.Bold, color = MesaGoBlack, fontSize = 18.sp)
                }
                Box(
                    Modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(MesaGoRed),
                    contentAlignment = Alignment.Center
                ) {
                    Text("✓", color = MesaGoWhite, fontSize = 10.sp)
                }
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text("Carlos Mendoza P.", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MesaGoBlack)
                Text(
                    "carlos.mendoza@live.uleam.edu.ec",
                    fontSize = 12.sp,
                    color = MesaGoGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    if (isManager) "Rol: Encargado ULEAM" else "Rol: Estudiante ULEAM",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MesaGoWhite,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(MesaGoRed)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, trailing: String? = null) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MesaGoRed)
        if (trailing != null) {
            Text(trailing, fontSize = 11.sp, color = MesaGoGray)
        }
    }
}

@Composable
private fun SettingsCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(Modifier.padding(vertical = 4.dp)) {
            content()
        }
    }
}

@Composable
private fun TogglePreferenceRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MesaGoRed, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium, color = MesaGoBlack, fontSize = 15.sp)
            Text(subtitle, fontSize = 12.sp, color = MesaGoGray)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = MesaGoRed,
                checkedThumbColor = MesaGoWhite
            )
        )
    }
}

@Composable
private fun LinkPreferenceRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    badge: String? = null,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MesaGoRed, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium, color = MesaGoBlack, fontSize = 15.sp)
            Text(subtitle, fontSize = 12.sp, color = MesaGoGray)
        }
        if (badge != null) {
            Text(
                badge,
                fontSize = 11.sp,
                color = MesaGoGray,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MesaGoGray.copy(alpha = 0.12f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = MesaGoGray)
    }
}

@Composable
private fun navColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = MesaGoRed,
    selectedTextColor = MesaGoRed,
    indicatorColor = MesaGoRed.copy(alpha = 0.12f),
    unselectedIconColor = MesaGoGray,
    unselectedTextColor = MesaGoGray
)

@Preview(showBackground = true, heightDp = 900)
@Composable
private fun PreviewSettings() {
    MaterialTheme { SettingsScreen() }
}
