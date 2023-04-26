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

// This class is an adapter for the RecyclerView that displays the list of characters.
class CharacterListAdapter(private val onCharacterClickListener: OnCharacterClickListener) :
    ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    // Creates a new ViewHolder for the RecyclerView.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {

        // Inflates the binding for the ViewHolder.
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    // Binds the data for a ViewHolder to its position in the list.
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        // Gets the character at the specified position and binds it to the ViewHolder.
        val character = getItem(position)
        holder.bind(character)
    }

    // A ViewHolder for the RecyclerView.
    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Binds the character data to the ViewHolder.
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

// This class defines how the RecyclerView determines whether the contents of two list items are the same.
class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    // Determines whether two items have the same ID.
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }

    // Determines whether the contents of two items are the same.
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}
