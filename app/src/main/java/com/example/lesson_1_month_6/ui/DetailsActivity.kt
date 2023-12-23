package com.example.lesson_1_month_6.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.lesson_1_month_6.data.BaseResponse
import com.example.lesson_1_month_6.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receiveData = intent.getSerializableExtra("characterModel") as BaseResponse.Character
        with(binding) {
            tvCharacterName.text = receiveData.name
            tvStatus.text = receiveData.status
            tvSpecies.text = receiveData.species
            tvLocationInfo.text = receiveData.location.name
            Glide.with(binding.imgCharacter).load(receiveData.image).into(binding.imgCharacter)
        }
    }
}