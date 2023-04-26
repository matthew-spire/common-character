package com.matthewspire.commoncharacter.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matthewspire.commoncharacter.data.model.Character

// This class is a ViewModel that holds the selected character to be displayed.
class CharacterDetailViewModel : ViewModel() {

    // The LiveData object for the character.
    private val _character = MutableLiveData<Character?>()

    // The public LiveData object for the character.
    val character: LiveData<Character?> = _character

    // Sets the character in the ViewModel.
    fun setCharacter(character: Character?) {
        _character.value = character
    }
}