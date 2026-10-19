package com.example.mesago.model

data class Dish(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val soldOut: Boolean = false,
    val visibleInCatalog: Boolean = true
)
