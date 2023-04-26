package com.matthewspire.commoncharacter.data.repository

import com.matthewspire.commonCharacter.BuildConfig
import com.matthewspire.commoncharacter.data.api.ApiService
import com.matthewspire.commoncharacter.data.model.ApiResult
import com.matthewspire.commoncharacter.data.model.Character

class CharacterRepository(private val apiService: ApiService) {
    suspend fun fetchCharacters(): List<Character> {
        val response = apiService.fetchCharacters(BuildConfig.CHARACTER_QUERY)
        return processResponse(response)
    }

    private fun processResponse(apiResult: ApiResult): List<Character> {
        return apiResult.relatedTopics.mapNotNull { relatedTopic ->
            val name = relatedTopic.text.split(" - ")[0]
            val description = relatedTopic.text.split(" - ")[1]

            if (name.isNotBlank() && description.isNotBlank()) {
                Character(
                    name = name,
                    description = description,
                    icon = relatedTopic.icon
                )
            } else {
                null
            }
        }
    }
}