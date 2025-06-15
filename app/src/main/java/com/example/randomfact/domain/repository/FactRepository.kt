package com.example.randomfact.domain.repository

import com.example.randomfact.domain.model.Fact

interface FactRepository {
    suspend fun getRandomFact(): Fact
}