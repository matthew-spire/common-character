package com.matthewspire.commoncharacter.ui.list

import com.matthewspire.commoncharacter.data.model.Character

interface OnCharacterClickListener {
    fun onCharacterClick(character: Character)
}