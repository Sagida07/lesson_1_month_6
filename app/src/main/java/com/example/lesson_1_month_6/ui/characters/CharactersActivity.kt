package com.example.lesson_1_month_6.ui.characters

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson_1_month_6.data.Resource
import com.example.lesson_1_month_6.databinding.ActivityCharactersBinding
import com.example.lesson_1_month_6.recycler.RMAdapter
import com.example.lesson_1_month_6.ui.base.BaseActivity
import com.example.lesson_1_month_6.ui.details.DetailsActivity
import com.example.lesson_1_month_6.ui.utils.RMKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : BaseActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private val viewModel: CharactersViewModel by viewModel()
    private val rmAdapter by lazy { RMAdapter(this::onClickItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharactersRecycler()

        viewModel.getCharacters().stateHandler(
            success = {
                rmAdapter.submitList(it)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
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