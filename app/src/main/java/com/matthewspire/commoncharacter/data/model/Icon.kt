package com.matthewspire.commoncharacter.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Icon(
    @Json(name = "Height")
    val height: String,

    @Json(name = "URL")
    val url: String,

    @Json(name = "Width")
    val width: String
) : Parcelable
