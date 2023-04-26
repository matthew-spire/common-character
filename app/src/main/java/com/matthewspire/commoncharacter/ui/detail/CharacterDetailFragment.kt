package com.matthewspire.commoncharacter.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.matthewspire.commonCharacter.R
import com.matthewspire.commonCharacter.databinding.FragmentCharacterDetailBinding
import com.matthewspire.commoncharacter.data.model.Character

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()
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
        viewModel.setCharacter(safeArgs?.character)

        viewModel.character.observe(viewLifecycleOwner) { character ->
            if (character == null) {
                showSelectCharacterMessage()
            } else {
                updateCharacter(character)
            }
        }
    }

    private fun showSelectCharacterMessage() {
        binding.selectCharacterMessage.text = getString(R.string.select_character)
        binding.selectCharacterMessage.visibility = View.VISIBLE
    }

    fun updateCharacter(character: Character?) {
        setupCharacterDetails(character)
        loadCharacterImage(character)
        controlSelectCharacterMessageVisibility(character)
    }

    private fun setupCharacterDetails(character: Character?) {
        // Display character details
        binding.apply {
            characterTitle.text = character?.name
            characterDescription.text = character?.description
        }
    }

    private fun loadCharacterImage(character: Character?) {
        // Load character image
        val imageUrl = character?.icon?.url?.let { "https://duckduckgo.com$it" } ?: ""
        val imageResource = imageUrl.ifEmpty { R.drawable.ic_placeholder }

        Glide.with(requireContext())
            .load(imageResource)
            .fitCenter()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(binding.characterImage)
    }

    private fun controlSelectCharacterMessageVisibility(character: Character?) {
        binding.selectCharacterMessage.visibility = if (character != null) View.GONE else View.VISIBLE
    }
}