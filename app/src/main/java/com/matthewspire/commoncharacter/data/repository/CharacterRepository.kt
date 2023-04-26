package com.matthewspire.commoncharacter.data.repository

import com.matthewspire.commonCharacter.BuildConfig
import com.matthewspire.commoncharacter.data.api.ApiService
import com.matthewspire.commoncharacter.data.model.ApiResult
import com.matthewspire.commoncharacter.data.model.Character

// This class is responsible for fetching and processing character data from the API.
class CharacterRepository(private val apiService: ApiService) {

    // Fetches a list of characters from the API and processes the response.
    // @return: A suspend function that returns a list of Character objects.
    suspend fun fetchCharacters(): List<Character> {

        // Makes a request to the API and retrieves the response.
        val response = apiService.fetchCharacters(BuildConfig.CHARACTER_QUERY)

        // Processes the response and returns a list of characters.
        return processResponse(response)
    }

    // Processes the response from the API and returns a list of characters.
    // @param apiResult: The API result to be processed.
    // @return: A list of Character objects.
    private fun processResponse(apiResult: ApiResult): List<Character> {

        // Maps the related topics returned by the API to Character objects.
        return apiResult.relatedTopics.mapNotNull { relatedTopic ->

            // Extracts the name and description of the character from the related topic.
            val name = relatedTopic.text.split(" - ")[0]
            val description = relatedTopic.text.split(" - ")[1]

            // If both the name and description are not blank, creates a new Character object.
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