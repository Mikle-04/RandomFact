package com.example.randomfact.domain.model

//Модель факта, который мы показываем в UI.
data class Fact(
    val id: Int? = null, // ID может быть null для случайного факта
    val text: String
)