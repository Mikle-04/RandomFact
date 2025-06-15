package com.example.randomfact.data.remote

import retrofit2.http.GET

//Интерфейс API для получения случайного факта.
interface FactApi {
    @GET("random.json?language=en")
    suspend fun getRandomFact(): FactResponse
}