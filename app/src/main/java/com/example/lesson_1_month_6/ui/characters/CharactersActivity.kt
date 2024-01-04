package com.example.lesson_1_month_6.ui.characters

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.data.Resource
import com.example.lesson_1_month_6.databinding.ActivityCharactersBinding
import com.example.lesson_1_month_6.recycler.RMAdapter
import com.example.lesson_1_month_6.ui.details.DetailsActivity
import com.example.lesson_1_month_6.ui.utils.RMKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private val rmAdapter by lazy { RMAdapter(this::onClickItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCharacters().observe(this) { result ->
            when (result) {
                is Resource.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }

                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Resource.Success -> {
                    rmAdapter.submitList(result.data)
                    binding.progressBar.isVisible = false
                }
            }

            setupCharactersRecycler()
        }
    }

    private fun setupCharactersRecycler() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(
            this@CharactersActivity, LinearLayoutManager.VERTICAL, false

        )
        adapter = rmAdapter
    }

    private fun onClickItem(characterId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(RMKeys.CHARACTER_ID_ARG, characterId)
        startActivity(intent)
    }
}