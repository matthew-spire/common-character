package com.matthewspire.commoncharacter.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matthewspire.commoncharacter.data.model.Character
import com.matthewspire.commoncharacter.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterListViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    val characterList = MutableLiveData<List<Character>>()

    val errorMessage = MutableLiveData<String>()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val characters = characterRepository.fetchCharacters()
                characterList.postValue(characters)
            } catch (exception: Exception) {
                errorMessage.postValue("Failed to fetch characters.")
            }
        }
    }
}