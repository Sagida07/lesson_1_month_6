package com.example.lesson_1_month_6.di

import com.example.lesson_1_month_6.ui.characters.CharactersViewModel
import com.example.lesson_1_month_6.ui.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharactersViewModel(get())
    }
    //for characters
    viewModel {
        DetailsViewModel(get())
    }
    //for details(desc)
}
