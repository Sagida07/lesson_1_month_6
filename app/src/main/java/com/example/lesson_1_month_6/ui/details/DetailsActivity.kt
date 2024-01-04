package com.example.lesson_1_month_6.ui.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lesson_1_month_6.R
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.databinding.ActivityDetailsBinding
import com.example.lesson_1_month_6.ui.Indicator
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
                setupCharacterData(it)
        }
    }

    private fun setupCharacterData(receiveData: Character) = with(binding) {
        tvCharacterName.text = receiveData.name
        tvStatus.text = receiveData.status
        tvSpecies.text = receiveData.species
        tvLocationInfo.text = receiveData.location.name
        Glide.with(imgCharacter).load(receiveData.image).into(imgCharacter)
        when (tvStatus.text.toString().uppercase()) {
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_dead)
        }
    }
}