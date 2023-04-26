package com.matthewspire.commoncharacter.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CharacterResult(
    @Json(name = "FirstURL")
    val firstURL: String,

    @Json(name = "Icon")
    val icon: Icon,

    @Json(name = "Result")
    val result: String,

    @Json(name = "Text")
    val text: String
) : Parcelable