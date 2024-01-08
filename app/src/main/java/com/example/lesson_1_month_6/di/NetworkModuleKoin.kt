package com.example.lesson_1_month_6.di

import org.koin.dsl.module

val networkModule = module {
    single {
        provideLoggingInterceptor()
    }

    single {
        provideOkHttpClient(get())
    }

    factory {
        provideRetrofit(get())
    }

    factory {
        provideRMApiService(get())
    }
}