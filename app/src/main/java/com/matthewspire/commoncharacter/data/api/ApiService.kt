package com.matthewspire.commoncharacter.data.api

import com.matthewspire.commoncharacter.data.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

// This interface defines the ApiService for making API calls.
interface ApiService {
    // Makes a GET request to the base URL with the specified query parameters.
    // @param query: The search query used to fetch characters.
    // @param format: The response format, default is JSON.
    // @return: A suspend function that returns an ApiResult object.
    @GET("/")
    suspend fun fetchCharacters(
        @Query("q") query: String,
        @Query("format") format: String = "json"
    ) : ApiResult
}