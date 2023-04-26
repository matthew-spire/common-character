package com.matthewspire.commoncharacter.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.matthewspire.commonCharacter.R
import com.matthewspire.commonCharacter.databinding.FragmentCharacterListBinding
import com.matthewspire.commoncharacter.data.model.Character
import com.matthewspire.commoncharacter.ServiceLocator
import com.matthewspire.commoncharacter.ui.detail.CharacterDetailFragment

// This class is a fragment that displays the list of characters.
class CharacterListFragment : Fragment(), OnCharacterClickListener {

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: CharacterListAdapter

    // Creates the view for the fragment.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Sets up the ViewModel, RecyclerView, and search functionality for the fragment.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupSearch()
        observeViewModel()
    }

    // Handles clicks on characters in the list.
    override fun onCharacterClick(character: Character) {
        val detailFragment = parentFragmentManager.findFragmentById(R.id.characterDetailFragment) as? CharacterDetailFragment

        if (detailFragment != null) {
            detailFragment.updateCharacter(character)
        } else {
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(character)
            findNavController().navigate(action)
        }
    }

    // Sets up the ViewModel for the fragment.
    private fun setupViewModel() {
        val factory = CharacterListViewModelFactory(ServiceLocator.characterRepository)
        viewModel = androidx.lifecycle.ViewModelProvider(this, factory)[CharacterListViewModel::class.java]
    }

    // Sets up the RecyclerView for the fragment.
    private fun setupRecyclerView() {
        adapter = CharacterListAdapter(this)

        binding.characterList.layoutManager = LinearLayoutManager(requireContext())
        binding.characterList.adapter = adapter
    }

    // Sets up the search functionality for the fragment.
    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterCharacters(newText)
                return true
            }
        })
    }

    // Observes changes in the ViewModel and updates the UI accordingly.
    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }
}