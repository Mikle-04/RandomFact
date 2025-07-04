package com.example.randomfact.di

import androidx.room.Room
import com.example.randomfact.data.db.FactDatabase
import com.example.randomfact.data.remote.FactApi
import com.example.randomfact.data.repository.FactRepositoryImpl
import com.example.randomfact.domain.repository.FactRepository
import com.example.randomfact.domain.usecase.GetRandomFactUseCase
import com.example.randomfact.presentation.FactViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{

    // Retrofit + FactApi
    single {
        Retrofit.Builder()
            .baseUrl("https://uselessfacts.jsph.pl/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FactApi::class.java)
    }

    // UseCase
    single { GetRandomFactUseCase(get()) }

    //Room
    single{
        Room.databaseBuilder(get(), FactDatabase:: class.java, "fact_db").build()
    }
    // FactDao
    single { get<FactDatabase>().factDao() }

    // Repository
    single<FactRepository> { FactRepositoryImpl(get(), get()) }

    // ViewModel
    viewModel{ FactViewModel(get(), get()) }
}