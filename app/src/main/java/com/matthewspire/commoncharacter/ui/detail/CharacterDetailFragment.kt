package com.matthewspire.commoncharacter.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.matthewspire.commonCharacter.R
import com.matthewspire.commonCharacter.databinding.FragmentCharacterDetailBinding
import com.matthewspire.commoncharacter.data.model.Character

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private var safeArgs: CharacterDetailFragmentArgs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nullableArgs = arguments
        if (nullableArgs != null) {
            safeArgs = CharacterDetailFragmentArgs.fromBundle(nullableArgs)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = safeArgs?.character

        if (character == null) {
            binding.selectCharacterMessage.text = getString(R.string.select_character)
        } else {
            updateCharacter(character)
        }
    }

    fun updateCharacter(character: Character?) {
        // Display character details
        binding.apply {
            characterTitle.text = character?.name
            characterDescription.text = character?.description
        }

        // Load character image
        if ((character?.icon?.url != null) && character.icon.url.isNotEmpty()) {
            val iconUrl = "https://duckduckgo.com${character.icon.url}"
            Glide.with(requireContext())
                .load(iconUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(binding.characterImage)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.ic_placeholder)
                .fitCenter()
                .into(binding.characterImage)
        }

        // Show or hide "Select a Character" message
        if (character != null) {
            binding.selectCharacterMessage.visibility = View.GONE
        } else {
            binding.selectCharacterMessage.visibility = View.VISIBLE
        }
    }
}