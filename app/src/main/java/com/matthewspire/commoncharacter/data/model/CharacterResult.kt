package com.matthewspire.commoncharacter.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

// Represents a character result returned by the API.
@JsonClass(generateAdapter = true)
@Parcelize
data class CharacterResult(
    // The first URL associated with the character.
    @Json(name = "FirstURL")
    val firstURL: String,

    // The icon associated with the character.
    // Contains a URL, which is used to get the character's image.
    @Json(name = "Icon")
    val icon: Icon,

    // The description of the character, including the HTML anchor element.
    @Json(name = "Result")
    val result: String,

    // The description of the character.
    @Json(name = "Text")
    val text: String
) : Parcelable