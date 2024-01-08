package com.example.lesson_1_month_6.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.data.Repository

class DetailsViewModel(
    private val repository: Repository
) : ViewModel() {

    fun getDetails(id: Int): LiveData<Character> = repository.getDetails(id)
}