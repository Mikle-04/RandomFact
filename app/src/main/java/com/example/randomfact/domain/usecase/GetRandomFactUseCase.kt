package com.example.randomfact.domain.usecase

import com.example.randomfact.domain.model.Fact
import com.example.randomfact.domain.repository.FactRepository

//UseCase для получения случайного факта.
class GetRandomFactUseCase(private val repository: FactRepository) {

    suspend operator fun invoke(): Fact{
        return repository.getRandomFact()
    }
}