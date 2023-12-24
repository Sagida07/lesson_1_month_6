package com.example.lesson_1_month_6.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.databinding.ActivityDetailsBinding
import com.example.lesson_1_month_6.ui.utils.RMKeys

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(RMKeys.CHARACTER_ID_ARG, 0)

        viewModel.getDetails(id).observe(this) {
            setupCharacterDAta(it)
        }
    }

    private fun setupCharacterDAta(receiveData: Character) = with(binding) {
        tvCharacterName.text = receiveData.name
        tvStatus.text = receiveData.status
        tvSpecies.text = receiveData.species
        tvLocationInfo.text = receiveData.location.name
        Glide.with(binding.imgCharacter).load(receiveData.image).into(binding.imgCharacter)
    }
}