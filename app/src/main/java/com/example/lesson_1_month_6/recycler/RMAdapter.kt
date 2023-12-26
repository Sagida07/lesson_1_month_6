package com.example.lesson_1_month_6.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson_1_month_6.R
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.databinding.ItemRmBinding
import com.example.lesson_1_month_6.ui.Indicator

class RMAdapter(
    private val onClick: (characterId: Int) -> Unit,
) :
    RecyclerView.Adapter<RMAdapter.RMViewHolder>() {
    private var list = listOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMViewHolder {
        return RMViewHolder(
            ItemRmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RMViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<Character>) {
        this.list = list
    }

    inner class RMViewHolder(private val binding: ItemRmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Character) = with(binding) {
            tvCharacterName.text = model.name
            tvStatus.text = model.status
            tvSpecies.text = model.species
            tvLocationInfo.text = model.location.name
            Glide.with(imgCharacter).load(model.image).into(imgCharacter)
            itemView.setOnClickListener { onClick(model.id) }

            when (tvStatus.text.toString().uppercase()) {
                Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_alive)
                Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_unknown)
                Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_dead)
            }
        }
    }
}