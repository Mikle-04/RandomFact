package com.example.randomfact.data.repository

import com.example.randomfact.data.db.dao.FactDao
import com.example.randomfact.data.db.model.FactEntity
import com.example.randomfact.data.remote.FactApi
import com.example.randomfact.domain.model.Fact
import com.example.randomfact.domain.repository.FactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Реализация репозитория для получения факта через API
class FactRepositoryImpl(
    private val factApi: FactApi,
    private val factDao: FactDao
) : FactRepository {

    override suspend fun getRandomFact(): Fact {
        val result = factApi.getRandomFact()
        return Fact(text = result.text)
    }

    override suspend fun addToFavorites(fact: Fact) {
        factDao.insertFact(FactEntity(text = fact.text))
    }

    override fun getFavorites(): Flow<List<Fact>> {
        return factDao.getAllFacts().map { entities ->
            entities.map { Fact(it.id, it.text) }
        }
    }

    override suspend fun deleteFavorite(id: Int) {
        factDao.deleteFactById(id)
    }

    override suspend fun clearFavorites() {
        factDao.clearAll()
    }
}