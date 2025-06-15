package com.example.randomfact.data.db.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.randomfact.data.db.model.FactEntity
import kotlinx.coroutines.flow.Flow

interface FactDao {

    // Вставляем новый факт в избранное
    @Insert
    suspend fun insertFact(fact: FactEntity)

    // Получаем все факты как Flow для реактивного UI
    @Query("SELECT * FROM facts")
    fun getAllFacts(): Flow<List<FactEntity>>

    // Удаляем факт по id
    @Query("DELETE FROM facts WHERE id = :id")
    suspend fun deleteFactById(id: Int)

    // Можно добавить очистку всех фактов
    @Query("DELETE FROM facts")
    suspend fun clearAll()
}