package com.matthewspire.commoncharacter.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.matthewspire.commoncharacter.data.repository.CharacterRepository

// This class is a factory class for creating instances of the CharacterListViewModel.
class CharacterListViewModelFactory(private val characterRepository: CharacterRepository) : ViewModelProvider.Factory {

    // Creates an instance of the CharacterListViewModel.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterListViewModel(characterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}