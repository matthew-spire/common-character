package com.matthewspire.commoncharacter.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.matthewspire.commonCharacter.databinding.FragmentCharacterListBinding
import com.matthewspire.commoncharacter.ServiceLocator

class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupViewModel() {
        val factory = CharacterListViewModelFactory(ServiceLocator.characterRepository)
        viewModel = androidx.lifecycle.ViewModelProvider(this, factory)[CharacterListViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = CharacterListAdapter { character ->
            // handle character click even
            TODO()
        }

        binding.characterList.layoutManager = LinearLayoutManager(requireContext())
        binding.characterList.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            // handle error message
            TODO()
        }
    }
}