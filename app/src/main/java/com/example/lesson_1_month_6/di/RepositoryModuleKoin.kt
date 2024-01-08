package com.example.lesson_1_month_6.di

import com.example.lesson_1_month_6.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        Repository(get())
    }
}