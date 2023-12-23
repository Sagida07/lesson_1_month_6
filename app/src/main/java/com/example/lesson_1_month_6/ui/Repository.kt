package com.example.lesson_1_month_6.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lesson_1_month_6.data.RMApiService
import com.example.lesson_1_month_6.data.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: RMApiService) {

    fun getCharacters(): MutableLiveData<BaseResponse> {
        val rm = MutableLiveData<BaseResponse>()

        api.getCharacters().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    rm.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.e("ERROR", "onFailure: ${t.localizedMessage}", )
                }
            })
            return rm
        }
    }