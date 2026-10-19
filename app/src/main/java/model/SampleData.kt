package com.example.mesago.model

import androidx.compose.runtime.mutableStateListOf

object SampleData {
    /** Lista compartida entre gestión del menú y formulario (sin ViewModel en esta etapa). */
    val managerDishes = mutableStateListOf(
        Dish(
            id = "1",
            name = "Pollo al horno",
            description = "Pollo horneado con menestra, arroz y ensalada.",
            price = 2.50
        ),
        Dish(
            id = "2",
            name = "Seco de carne",
            description = "Seco de res con arroz, maduro y aguacate.",
            price = 2.75
        ),
        Dish(
            id = "3",
            name = "Encebollado",
            description = "Pescado, yuca, cebolla encurtida y chifles.",
            price = 2.25,
            soldOut = true
        ),
        Dish(
            id = "4",
            name = "Jugo natural",
            description = "Fruta de temporada, vaso 12 oz.",
            price = 1.00
        ),
        Dish(
            id = "5",
            name = "Almuerzo ejecutivo",
            description = "Sopa, plato fuerte, postre y bebida.",
            price = 3.00
        )
    )
}
