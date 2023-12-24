package com.example.lesson_1_month_6.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RMApiService {
    @GET("character")
    fun getCharacters(): Call<BaseResponse<Character>>

    @GET("character/{id}")
    fun getDetails(
        @Path("id") id: Int
    ): Call<Character>
}