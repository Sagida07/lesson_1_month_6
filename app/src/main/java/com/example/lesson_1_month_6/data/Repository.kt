package com.example.lesson_1_month_6.data

import androidx.lifecycle.LiveData
import com.example.lesson_1_month_6.ui.base.BaseRepository

class Repository(private val api: RMApiService) : BaseRepository(api) {

    fun getCharacters(): LiveData<Resource<List<Character>>> = request {
        api.getCharacters().body()?.results ?: emptyList()
    }

    fun getDetails(id: Int): LiveData<Resource<Character>> = request {
        api.getDetails(id).body()!!
    }
}