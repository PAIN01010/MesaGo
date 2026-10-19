package com.example.mesago.screens

import androidx.annotation.DrawableRes
import com.example.mesago.R

// Miniatura cuadrada (lista del menú). Devuelve null si el plato no tiene foto.
@DrawableRes
fun imagenDePlato(nombre: String): Int? = when (nombre.trim().lowercase()) {
    "pollo al horno" -> R.drawable.plato_pollo_al_horno
    "seco de carne" -> R.drawable.plato_seco_de_carne
    "encebollado" -> R.drawable.plato_encebollado
    "jugo natural" -> R.drawable.plato_jugo_natural
    // "almuerzo ejecutivo" -> R.drawable.plato_almuerzo_ejecutivo  // pendiente: agregar foto
    else -> null
}

// Foto horizontal (pantalla de detalle).
@DrawableRes
fun imagenDetalleDePlato(nombre: String): Int? = when (nombre.trim().lowercase()) {
    "pollo al horno" -> R.drawable.plato_detalle_pollo_al_horno
    "seco de carne" -> R.drawable.plato_detalle_seco_de_carne
    "encebollado" -> R.drawable.plato_detalle_encebollado
    "jugo natural" -> R.drawable.plato_detalle_jugo_natural
    // "almuerzo ejecutivo" -> R.drawable.plato_detalle_almuerzo_ejecutivo  // pendiente
    else -> null
}