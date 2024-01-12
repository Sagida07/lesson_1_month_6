package com.example.lesson_1_month_6.di

import com.example.lesson_1_month_6.BuildConfig
import com.example.lesson_1_month_6.data.RMApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideRMApiService(
    retrofit: Retrofit
): RMApiService {
    return retrofit.create(RMApiService::class.java)
}