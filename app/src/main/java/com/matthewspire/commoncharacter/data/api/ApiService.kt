package com.matthewspire.commoncharacter.data.api

import com.matthewspire.commoncharacter.data.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun fetchCharacters(
        @Query("q") query: String,
        @Query("format") format: String = "json"
    ) : ApiResult
}