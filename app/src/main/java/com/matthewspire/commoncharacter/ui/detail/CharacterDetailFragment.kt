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

// This class is a fragment that displays the details of a selected character.
class CharacterDetailFragment : Fragment() {

    // The binding for the fragment.
    private lateinit var binding: FragmentCharacterDetailBinding

    // The ViewModel associated with the fragment.
    private val viewModel: CharacterDetailViewModel by viewModels()

    // The safe arguments for the fragment.
    private var safeArgs: CharacterDetailFragmentArgs? = null

    // Called when the fragment is created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieves the arguments for the fragment and assigns them to safeArgs.
        val nullableArgs = arguments
        if (nullableArgs != null) {
            safeArgs = CharacterDetailFragmentArgs.fromBundle(nullableArgs)
        }
    }

    // Called to create the view hierarchy for the fragment.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflates the binding for the fragment.
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called when the view hierarchy is created.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets the character in the ViewModel to the selected character.
        viewModel.setCharacter(safeArgs?.character)

        // Observes the character LiveData and updates the UI accordingly.
        viewModel.character.observe(viewLifecycleOwner) { character ->
            if (character == null) {
                showSelectCharacterMessage()
            } else {
                updateCharacter(character)
            }
        }
    }

    // Displays a message to select a character.
    private fun showSelectCharacterMessage() {
        binding.selectCharacterMessage.text = getString(R.string.select_character)
        binding.selectCharacterMessage.visibility = View.VISIBLE
    }

    // Updates the UI with the character details.
    fun updateCharacter(character: Character?) {
        setupCharacterDetails(character)
        loadCharacterImage(character)
        controlSelectCharacterMessageVisibility(character)
    }

    // Sets up the character details in the UI.
    private fun setupCharacterDetails(character: Character?) {
        binding.apply {
            characterTitle.text = character?.name
            characterDescription.text = character?.description
        }
    }

    // Loads the character image into the ImageView.
    private fun loadCharacterImage(character: Character?) {
        val imageUrl = character?.icon?.url?.let { "https://duckduckgo.com$it" } ?: ""
        val imageResource = imageUrl.ifEmpty { R.drawable.ic_placeholder }

        Glide.with(requireContext())
            .load(imageResource)
            .fitCenter()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(binding.characterImage)
    }

    // Controls the visibility of the select character message.
    private fun controlSelectCharacterMessageVisibility(character: Character?) {
        binding.selectCharacterMessage.visibility = if (character != null) View.GONE else View.VISIBLE
    }
}