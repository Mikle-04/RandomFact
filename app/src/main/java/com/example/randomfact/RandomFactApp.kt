package com.example.randomfact

import android.app.Application
import com.example.randomfact.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RandomFactApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RandomFactApp)
            modules(appModule)
        }
    }
}