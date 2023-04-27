package com.matthewspire.commoncharacter.data.repository

import android.util.Log
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
        return try {
            // Iterate through the related topics and map each topic to a Character object
            // or null if the topic does not meet the expected format.
            apiResult.relatedTopics.mapNotNull { relatedTopic ->

                // Split the related topic text by " - " to separate the name and description.
                val splitText = relatedTopic.text.split(" - ")

                // Check if the split text contains at least two elements (name and description).
                if (splitText.size >= 2) {
                    val name = splitText[0]
                    val description = splitText[1]

                    // If both the name and description are not blank, create a new Character object.
                    if (name.isNotBlank() && description.isNotBlank()) {
                        Character(
                            name = name,
                            description = description,
                            icon = relatedTopic.icon
                        )
                    } else {
                        null
                    }
                } else {
                    // Log a warning if the related topic's text is in an unexpected format.
                    Log.w("CharacterRepository", "Skipping related topic due to unexpected format: ${relatedTopic.text}")
                    null
                }
            }
        } catch (exception: Exception) {
            // Log an error if there was an issue processing the response and rethrow the exception.
            Log.e("CharacterRepository", "Error processing response: $exception")
            throw exception
        }
    }
}