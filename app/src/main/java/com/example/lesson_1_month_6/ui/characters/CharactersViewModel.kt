package com.example.lesson_1_month_6.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: Repository):ViewModel(){

    fun getCharacters(): LiveData<List<Character>> = repository.getCharacters()
    }