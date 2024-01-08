package com.example.lesson_1_month_6.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.data.Repository
import com.example.lesson_1_month_6.data.Resource

class CharactersViewModel(
    private val repository: Repository
) : ViewModel() {
    fun getCharacters(): LiveData<Resource<List<Character>>> =
        repository.getCharacters()
}