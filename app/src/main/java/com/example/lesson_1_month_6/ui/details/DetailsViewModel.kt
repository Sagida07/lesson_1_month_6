package com.example.lesson_1_month_6.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.data.Repository
import com.example.lesson_1_month_6.data.Resource

class DetailsViewModel(
    private val repository: Repository
) : ViewModel() {

    fun getDetails(id: Int): LiveData<Resource<Character>> = repository.getDetails(id)
}