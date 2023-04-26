package com.matthewspire.commoncharacter.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.matthewspire.commonCharacter.R
import com.matthewspire.commonCharacter.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

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
        val character = args.character

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
    }
}