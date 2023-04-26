package com.matthewspire.commoncharacter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Represents the response from the API, which contains a list of CharacterResult objects.
@JsonClass(generateAdapter = true)
data class ApiResult(
    // The list of related topics returned by the API.
    @Json(name="RelatedTopics")
    val relatedTopics: List<CharacterResult>
)
