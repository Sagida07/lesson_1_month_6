package com.example.lesson_1_month_6.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class Repository(private val api: RMApiService) {

    fun getCharacters(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = api.getCharacters()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    emit(Resource.Success(it.results))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "ERROR"))
        }
    }

    fun getDetails(id: Int): LiveData<Character> = liveData(Dispatchers.IO) {

        try {
            val cartoon = api.getDetails(id)
            if (cartoon.isSuccessful) {
                cartoon.body()?.let {
                    emit(it)
                }
            }
        } catch (v: Exception) {
            Log.e("failure", "getDetails")
        }
    }
}