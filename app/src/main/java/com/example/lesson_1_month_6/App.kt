package com.example.lesson_1_month_6

import android.app.Application
import com.example.lesson_1_month_6.di.rmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(rmModule)
        }
    }
}