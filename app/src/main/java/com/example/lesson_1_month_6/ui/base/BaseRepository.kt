package com.example.lesson_1_month_6.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.lesson_1_month_6.data.RMApiService
import com.example.lesson_1_month_6.data.Resource
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository(private val api: RMApiService) {
    protected fun <T> request(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
}