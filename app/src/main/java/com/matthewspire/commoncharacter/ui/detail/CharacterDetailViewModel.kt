package com.matthewspire.commoncharacter.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matthewspire.commoncharacter.data.model.Character

class CharacterDetailViewModel : ViewModel() {
    private val _character = MutableLiveData<Character?>()
    val character: LiveData<Character?> = _character

    fun setCharacter(character: Character?) {
        _character.value = character
    }
}