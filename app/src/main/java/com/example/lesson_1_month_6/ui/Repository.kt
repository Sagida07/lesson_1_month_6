package com.example.lesson_1_month_6.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lesson_1_month_6.data.RMApiService
import com.example.lesson_1_month_6.data.BaseResponse
import com.example.lesson_1_month_6.data.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: RMApiService) {

    fun getCharacters(): MutableLiveData<List<Character>> {
        val rm = MutableLiveData<List<Character>>()

        api.getCharacters().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                    rm.postValue(it.results)
                }
            }}

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                Log.e("ERROR", "onFailure: ${t.localizedMessage}")
            }
        })
        return rm
    }
}