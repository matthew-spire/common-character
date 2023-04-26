package com.matthewspire.commoncharacter.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

// Represents a character in the application.
@JsonClass(generateAdapter = true)
@Parcelize
data class Character(
    // The name of the character.
    val name: String,

    // The description of the character.
    val description: String,

    // The icon associated with the character.
    val icon: Icon
) : Parcelable
