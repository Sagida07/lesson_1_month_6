package com.example.lesson_1_month_6.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.lesson_1_month_6.R
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.databinding.ItemRmBinding
import com.example.lesson_1_month_6.ui.Indicator

class RMAdapter(
    private val onClick: (characterId: Int) -> Unit,
) : ListAdapter<Character, RMViewHolder>(
    RMDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RMViewHolder {
        return RMViewHolder(
            ItemRmBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), onClick
        )
    }

    override fun onBindViewHolder(holder: RMViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RMViewHolder(
    private val binding: ItemRmBinding,
    private val onClick: (characterId: Int) -> Unit
) : ViewHolder(binding.root) {
    fun bind(model: Character) = with(binding) {
        tvCharacterName.text = model.name
        tvStatus.text = model.status
        tvSpecies.text = model.species
        tvLocationInfo.text = model.location.name
        itemView.setOnClickListener { onClick(model.id) }
        Glide.with(imgCharacter).load(model.image).into(imgCharacter)

        when (tvStatus.text.toString().uppercase()) {
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.ind_dead)
        }
    }
}

class RMDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
}