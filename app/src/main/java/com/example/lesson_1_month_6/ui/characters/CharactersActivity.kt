package com.example.lesson_1_month_6.ui.characters

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.databinding.ActivityCharactersBinding
import com.example.lesson_1_month_6.recycler.RMAdapter
import com.example.lesson_1_month_6.ui.details.DetailsActivity
import com.example.lesson_1_month_6.ui.utils.RMKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val rmViewModel: ViewModel by viewModels()

    private val rmAdapter by lazy { RMAdapter(this::onClickItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharactersRecycler()

        setContentView(binding.root)
        rmViewModel.getCharacters.observe(this) {
            rmAdapter.submitList(it)
            setupCharactersRecycler()
        }
    }

    private fun setupCharactersRecycler() = with(binding.recyclerView) {
        adapter = this@CharactersActivity.rmAdapter
        layoutManager = LinearLayoutManager(
            this@CharactersActivity, LinearLayoutManager.VERTICAL, false

        )
        adapter = rmAdapter
    }

    private fun onClickItem(character: Character) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(RMKeys.CHARACTER_ID_ARG, character.id)
        startActivity(intent)
    }
}