@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mesago.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.R
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

private val FondoPantalla = Color(0xFFF7F5F3)
private val RosaSuave = Color(0xFFFCE4EC)

/** Tras iniciar sesión o registrarse: el usuario elige rol estudiante o encargado. */
@Composable
fun RoleSelectionScreen(
    onStudentClick: () -> Unit = {},
    onManagerClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = FondoPantalla,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "MesaGo",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MesaGoRed
                        )
                        Text("ULEAM · Selección de rol", fontSize = 12.sp, color = MesaGoGray)
                    }
                },
                actions = {
                    Text(
                        "✔ ULEAM",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MesaGoRed,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(RoundedCornerShape(50))
                            .background(RosaSuave)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoPantalla)
            )
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
                    .widthIn(max = 520.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mesago_logo),
                    contentDescription = "Logo MesaGo",
                    modifier = Modifier.size(108.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "¿Cómo quieres ingresar?",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MesaGoBlack,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Elige tu experiencia según tu rol en el comedor universitario.",
                    fontSize = 14.sp,
                    color = MesaGoGray,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(RosaSuave)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("ℹ️", fontSize = 14.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Puedes cambiar de rol más tarde desde Configuración.",
                        fontSize = 12.sp,
                        color = MesaGoBlack,
                        lineHeight = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "SELECCIONA TU ROL",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MesaGoRed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )

                RoleCard(
                    title = "Soy estudiante",
                    subtitle = "Consulta menús, precios y disponibilidad en los Tasty ULEAM.",
                    detail = "Ver puntos Tasty · menú del día · detalle de platos",
                    icon = Icons.Default.School,
                    onClick = onStudentClick
                )

                Spacer(modifier = Modifier.height(14.dp))

                RoleCard(
                    title = "Soy encargado",
                    subtitle = "Administra el menú de tu Tasty: platos, precios y vitrina.",
                    detail = "Gestión del menú · agregar o editar platos",
                    icon = Icons.Default.Storefront,
                    onClick = onManagerClick
                )

                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Composable
private fun RoleCard(
    title: String,
    subtitle: String,
    detail: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MesaGoRed,
                modifier = Modifier.size(44.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MesaGoBlack
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = subtitle, fontSize = 13.sp, color = MesaGoGray, lineHeight = 18.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = detail,
                    fontSize = 11.sp,
                    color = MesaGoRed.copy(alpha = 0.85f),
                    fontWeight = FontWeight.Medium
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MesaGoRed,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 780)
@Composable
private fun PreviewRoleSelection() {
    MaterialTheme { RoleSelectionScreen() }
}
