package com.matthewspire.commoncharacter.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Character(
    val name: String,
    val description: String,
    val icon: Icon
) : Parcelable
