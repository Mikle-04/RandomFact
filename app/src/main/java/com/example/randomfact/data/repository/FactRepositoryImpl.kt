package com.example.randomfact.data.repository

import com.example.randomfact.data.remote.FactApi
import com.example.randomfact.domain.model.Fact
import com.example.randomfact.domain.repository.FactRepository

//Реализация репозитория для получения факта через API
class FactRepositoryImpl(private val factApi: FactApi) : FactRepository {

    override suspend fun getRandomFact(): Fact {
        val result = factApi.getRandomFact()
        return Fact(result.text)
    }

}