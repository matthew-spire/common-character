package com.matthewspire.commoncharacter

import com.matthewspire.commoncharacter.data.api.ApiService
import com.matthewspire.commoncharacter.data.api.NetworkModule
import com.matthewspire.commoncharacter.data.repository.CharacterRepository

// This object provides a single point of access to the app's services.
object ServiceLocator {
    // Lazy initialization of the ApiService.
    private val apiService: ApiService by lazy {
        NetworkModule.apiService
    }

    // The CharacterRepository that depends on the ApiService.
    val characterRepository: CharacterRepository by lazy { CharacterRepository(apiService) }
}
