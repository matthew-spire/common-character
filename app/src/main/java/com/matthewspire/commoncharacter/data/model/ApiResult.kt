package com.matthewspire.commoncharacter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResult(
    @Json(name="RelatedTopics")
    val relatedTopics: List<CharacterResult>
)
