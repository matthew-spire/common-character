package com.matthewspire.commoncharacter.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.matthewspire.commonCharacter.R
import com.matthewspire.commonCharacter.databinding.ItemCharacterBinding
import com.matthewspire.commoncharacter.data.model.Character
import com.matthewspire.commoncharacter.ui.detail.CharacterDetailFragment

class CharacterListAdapter(private val onCharacterClickListener: OnCharacterClickListener) :
    ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(CharacterDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                characterName.text = character.name
                root.setOnClickListener {
                    onCharacterClickListener.onCharacterClick(character)
                }
            }
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}
