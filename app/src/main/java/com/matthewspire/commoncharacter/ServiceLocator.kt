package com.matthewspire.commoncharacter

import com.matthewspire.commoncharacter.data.api.ApiService
import com.matthewspire.commoncharacter.data.api.NetworkModule
import com.matthewspire.commoncharacter.data.repository.CharacterRepository

object ServiceLocator {
    private val apiService: ApiService by lazy {
        NetworkModule.apiService
    }

    val characterRepository: CharacterRepository by lazy { CharacterRepository(apiService) }
}