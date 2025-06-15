package com.example.randomfact.domain.repository

import com.example.randomfact.domain.model.Fact
import kotlinx.coroutines.flow.Flow

interface FactRepository {
    suspend fun getRandomFact(): Fact

    // Добавить факт в избранное
    suspend fun addToFavorites(fact: Fact)

    // Получить все избранные факты как Flow
    fun getFavorites(): Flow<List<Fact>>

    // Удалить факт из избранного по id
    suspend fun deleteFavorite(id: Int)

    // Полностью очистить избранное
    suspend fun clearFavorites()
}