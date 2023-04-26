package com.matthewspire.commoncharacter.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

// Represents an icon associated with a character.
@JsonClass(generateAdapter = true)
@Parcelize
data class Icon(
    // The height of the icon.
    @Json(name = "Height")
    val height: String,

    // The URL of the icon.
    @Json(name = "URL")
    val url: String,

    // The width of the icon.
    @Json(name = "Width")
    val width: String
) : Parcelable
