@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mesago.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mesago.model.Dish
import com.example.mesago.model.SampleData
import com.example.mesago.ui.theme.MesaGoBlack
import com.example.mesago.ui.theme.MesaGoGray
import com.example.mesago.ui.theme.MesaGoRed
import com.example.mesago.ui.theme.MesaGoWhite

private val FondoPantalla = Color(0xFFF7F5F3)
private val RosaSuave = Color(0xFFFCE4EC)

/**
 * @param dishId null = agregar plato; con id = editar plato existente.
 */
@Composable
fun DishFormScreen(
    dishId: String? = null,
    onBack: () -> Unit = {},
    onCancel: () -> Unit = onBack
) {
    val dishes = SampleData.managerDishes
    val existing = dishId?.let { id -> dishes.find { it.id == id } }
    val isNew = existing == null

    var nombre by rememberSaveable(dishId) { mutableStateOf(existing?.name ?: "") }
    var descripcion by rememberSaveable(dishId) { mutableStateOf(existing?.description ?: "") }
    var precio by rememberSaveable(dishId) {
        mutableStateOf(existing?.price?.let { "%.2f".format(it) } ?: "2.50")
    }
    var agotado by rememberSaveable(dishId) { mutableStateOf(existing?.soldOut ?: false) }
    var visible by rememberSaveable(dishId) { mutableStateOf(existing?.visibleInCatalog ?: true) }
    var error by rememberSaveable { mutableStateOf(false) }

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = MesaGoBlack,
        unfocusedTextColor = MesaGoBlack,
        focusedLabelColor = MesaGoRed,
        unfocusedLabelColor = MesaGoGray,
        cursorColor = MesaGoRed,
        focusedBorderColor = MesaGoRed,
        unfocusedBorderColor = MesaGoGray.copy(alpha = 0.5f)
    )

    Scaffold(
        containerColor = FondoPantalla,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = MesaGoBlack)
                    }
                },
                title = {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                if (isNew) "Agregar plato" else "Editar plato",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MesaGoBlack
                            )
                            if (isNew) {
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Nuevo",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MesaGoRed,
                                    modifier = Modifier
                                        .background(RosaSuave, RoundedCornerShape(50))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text("Tasty Central · Campus ULEAM", fontSize = 12.sp, color = MesaGoGray)
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .background(MesaGoRed.copy(alpha = 0.1f), RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Icon(Icons.Default.Shield, contentDescription = null, tint = MesaGoRed, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Encargado", fontSize = 11.sp, color = MesaGoRed, fontWeight = FontWeight.SemiBold)
                    }
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
                    .widthIn(max = 640.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                FormSection(title = "FOTOGRAFÍA DEL PLATO") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            Modifier
                                .size(88.dp)
                                .background(MesaGoGray.copy(alpha = 0.12f), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.BrokenImage, contentDescription = null, tint = MesaGoGray, modifier = Modifier.size(32.dp))
                            Icon(
                                Icons.Default.AddAPhoto,
                                contentDescription = null,
                                tint = MesaGoRed,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(6.dp)
                                    .size(18.dp)
                            )
                        }
                        Spacer(Modifier.width(14.dp))
                        Column(Modifier.weight(1f)) {
                            OutlinedButton(
                                onClick = { },
                                shape = RoundedCornerShape(24.dp),
                                border = BorderStroke(1.dp, MesaGoRed),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Seleccionar imagen", color = MesaGoRed, fontSize = 13.sp)
                            }
                            Spacer(Modifier.height(6.dp))
                            Text(
                                "Formato JPG, PNG o WebP. Máx. 5MB. Foto nítida para los estudiantes.",
                                fontSize = 11.sp,
                                color = MesaGoGray,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }

                FormSection(title = null) {
                    FieldLabel("Nombre del plato *", trailing = "Requerido")
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        placeholder = { Text("Ej. Pollo al horno con menestra", fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.DinnerDining, contentDescription = null, tint = MesaGoGray) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors
                    )

                    Spacer(Modifier.height(12.dp))

                    FieldLabel("Descripción y acompañamientos", trailing = "Opcional")
                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        placeholder = {
                            Text(
                                "Ingredientes, porciones y acompañamientos incluidos en la tarifa estudiante.",
                                fontSize = 14.sp
                            )
                        },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors
                    )

                    Spacer(Modifier.height(12.dp))

                    FieldLabel("Precio oficial (USD) *", trailing = "Tarifa estudiante ULEAM")
                    OutlinedTextField(
                        value = precio,
                        onValueChange = { precio = it },
                        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null, tint = MesaGoRed) },
                        trailingIcon = { Text("USD", color = MesaGoGray, modifier = Modifier.padding(end = 12.dp)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors
                    )
                }

                FormSection(title = "DISPONIBILIDAD EN VITRINA") {
                    Row(verticalAlignment = Alignment.Top) {
                        Checkbox(
                            checked = agotado,
                            onCheckedChange = { agotado = it },
                            colors = CheckboxDefaults.colors(checkedColor = MesaGoRed)
                        )
                        Column(Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Warning, contentDescription = null, tint = MesaGoRed, modifier = Modifier.size(18.dp))
                                Spacer(Modifier.width(6.dp))
                                Text("Marcar como agotado", fontWeight = FontWeight.Medium, color = MesaGoBlack)
                            }
                            Text(
                                "Los estudiantes verán el indicador gris «Agotado» en el catálogo.",
                                fontSize = 12.sp,
                                color = MesaGoGray
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Visibility, contentDescription = null, tint = MesaGoRed, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Visible en catálogo", fontWeight = FontWeight.Medium, color = MesaGoBlack)
                            Text("Publicado en la lista del comedor", fontSize = 12.sp, color = MesaGoGray)
                        }
                        Switch(
                            checked = visible,
                            onCheckedChange = { visible = it },
                            colors = SwitchDefaults.colors(checkedTrackColor = MesaGoRed, checkedThumbColor = MesaGoWhite)
                        )
                    }
                }

                if (error) {
                    Text(
                        "Completa el nombre y un precio válido.",
                        color = MesaGoRed,
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = {
                        val p = precio.replace(',', '.').toDoubleOrNull()
                        if (nombre.isBlank() || p == null) {
                            error = true
                        } else {
                            error = false
                            val plato = Dish(
                                id = existing?.id ?: System.currentTimeMillis().toString(),
                                name = nombre.trim(),
                                description = descripcion.trim(),
                                price = p,
                                soldOut = agotado,
                                visibleInCatalog = visible
                            )
                            if (existing == null) {
                                dishes.add(plato)
                            } else {
                                val i = dishes.indexOfFirst { it.id == existing.id }
                                if (i >= 0) dishes[i] = plato
                            }
                            onBack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MesaGoRed)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = MesaGoWhite)
                    Spacer(Modifier.width(8.dp))
                    Text("Guardar plato", color = MesaGoWhite, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                }

                TextButton(onClick = onCancel, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Cancelar y volver a la lista", color = MesaGoGray, fontSize = 14.sp)
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun FormSection(title: String?, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MesaGoWhite),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            if (title != null) {
                Text(
                    title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MesaGoGray,
                    letterSpacing = 0.8.sp
                )
                Spacer(Modifier.height(12.dp))
            }
            content()
        }
    }
}

@Composable
private fun FieldLabel(text: String, trailing: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, fontWeight = FontWeight.Medium, color = MesaGoBlack, fontSize = 14.sp)
        Text(trailing, fontSize = 11.sp, color = MesaGoGray)
    }
}

@Preview(showBackground = true, heightDp = 900)
@Composable
private fun PreviewDishFormNew() {
    com.example.mesago.ui.theme.MesaGoTheme { DishFormScreen(dishId = null) }
}

@Preview(showBackground = true, heightDp = 900)
@Composable
private fun PreviewDishFormEdit() {
    com.example.mesago.ui.theme.MesaGoTheme { DishFormScreen(dishId = "1") }
}
