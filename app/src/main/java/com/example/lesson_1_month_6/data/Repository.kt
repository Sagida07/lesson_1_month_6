package com.example.lesson_1_month_6.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val api: RMApiService) {

    fun getCharacters(): MutableLiveData<Resource<List<Character>>> {
        val rm = MutableLiveData<Resource<List<Character>>>()
        rm.postValue(Resource.Loading())

        api.getCharacters().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        rm.postValue(Resource.Success(it.results))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                rm.postValue(Resource.Error(t.localizedMessage ?: "Error"))
            }
        })
        return rm
    }

    fun getDetails(id: Int): MutableLiveData<Character> {
        val rm = MutableLiveData<Character>()

        api.getDetails(id).enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        rm.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("ERROR", "onFailure: ${t.localizedMessage}")
            }
        })
        return rm
    }
}