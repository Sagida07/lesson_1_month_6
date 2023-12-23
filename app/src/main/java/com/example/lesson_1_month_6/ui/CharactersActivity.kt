package com.example.lesson_1_month_6.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_1_month_6.data.BaseResponse
import com.example.lesson_1_month_6.databinding.ActivityCharactersBinding
import com.example.lesson_1_month_6.recycler.RMAdapter
import com.example.lesson_1_month_6.ui.utils.RMKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charactersViewModel.getCharacters().observe(this) {
            val adapter = RMAdapter(this::onClickItem, it.results)
            binding.recyclerView.adapter = adapter
        }
    }

    private fun onClickItem(rmModel: BaseResponse.Character) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(RMKeys.CHARACTER_ID_ARG, rmModel)
        startActivity(intent)
    }
}