package com.matthewspire.commoncharacter.ui.list

import com.matthewspire.commoncharacter.data.model.Character

// This interface defines a listener for when a character in the list is clicked.
interface OnCharacterClickListener {
    fun onCharacterClick(character: Character)
}
