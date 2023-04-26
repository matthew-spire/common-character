package com.matthewspire.commoncharacter.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matthewspire.commoncharacter.data.model.Character
import com.matthewspire.commoncharacter.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterListViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    private val _allCharacters = MutableLiveData<List<Character>>()
    private val _characters = MutableLiveData<List<Character>?>()
    val characters: MutableLiveData<List<Character>?> = _characters

    val errorMessage = MutableLiveData<String>()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val characters = characterRepository.fetchCharacters()
                _allCharacters.postValue(characters)
                _characters.postValue(characters)
            } catch (exception: Exception) {
                errorMessage.postValue("Failed to fetch characters.")
            }
        }
    }

    fun filterCharacters(query: String?) {
        val filteredList = if (query.isNullOrEmpty()) {
            _allCharacters.value
        } else {
            _allCharacters.value?.filter { character ->
                character.name.contains(query, ignoreCase = true) ||
                character.description.contains(query, ignoreCase = true)
            }
        }
        _characters.value = filteredList
    }
}