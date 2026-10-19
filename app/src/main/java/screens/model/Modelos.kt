package com.example.mesago.model

// ARCHIVO COMPARTIDO. Si otro compañero ya lo subió a GitHub, haz pull y NO lo dupliques.

data class Tasty(
    val nombre: String,
    val ubicacion: String,
    val abierto: Boolean,
    val sigla: String = "",
    val horario: String = "",
    val descripcion: String = ""
)

data class Plato(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val agotado: Boolean
)

val tastysDemo = listOf(
    Tasty("Tasty Central", "Edificio Central · Planta Baja", true, "PB", "07:30 - 16:00", "Menú ejecutivo y platos a la carta"),
    Tasty("Tasty Ciencias", "Facultad de Ciencias de la Vida", true, "FCV", "08:00 - 15:30", "Almuerzos universitarios y snacks"),
    Tasty("Tasty Salud", "Facultad de Ciencias de la Salud", true, "FCS", "07:30 - 15:00", "Opciones saludables e infusiones"),
    Tasty("Tasty Ingeniería", "Facultad de Ingeniería y Tecnología", true, "FIT", "08:00 - 15:30", "Desayunos, almuerzos y cafetería")
)

val platosDemo = listOf(
    Plato("Seco de pollo", "Arroz, seco de pollo, ensalada y maduro", 3.50, "Almuerzos", false),
    Plato("Encebollado", "Encebollado de albacora con yuca y chifles", 3.00, "Almuerzos", true),
    Plato("Jugo natural", "Jugo de naranja o maracuyá, 12 oz", 1.00, "Bebidas", false),
    Plato("Empanada de verde", "Rellena de queso, porción de 2 unidades", 1.25, "Snacks", false)
)