package com.example.lesson_1_month_6.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RMApiService {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse<Character>>

    @GET("character/{id}")
    suspend fun getDetails(
        @Path("id") id: Int
    ): Response<Character>
}