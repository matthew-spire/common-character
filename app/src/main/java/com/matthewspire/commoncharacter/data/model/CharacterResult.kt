package com.matthewspire.commoncharacter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResult(
    @Json(name = "FirstURL")
    val firstURL: String,

    @Json(name = "Icon")
    val icon: Icon,

    @Json(name = "Result")
    val result: String,

    @Json(name = "Text")
    val text: String
)
