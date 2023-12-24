package com.example.lesson_1_month_6.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson_1_month_6.data.Character
import com.example.lesson_1_month_6.databinding.ItemRmBinding

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
            Glide.with(imgCharacter).load(model.image).into(imgCharacter)
            tvCharacterName.text = model.name
            tvStatus.text = model.status
            tvSpecies.text = model.species
            tvLocationInfo.text = model.location.name
            itemView.setOnClickListener { onClick(model.id) }
        }
    }
}