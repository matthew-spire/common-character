package com.matthewspire.commoncharacter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Icon(
    @Json(name = "Height")
    val height: String,

    @Json(name = "URL")
    val url: String,

    @Json(name = "Width")
    val width: String
)
