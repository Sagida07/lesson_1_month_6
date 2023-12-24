package com.example.lesson_1_month_6.data

import retrofit2.Call
import retrofit2.http.GET

interface RMApiService {
        @GET("character")
        fun getCharacters(): Call<BaseResponse<Character>>
}