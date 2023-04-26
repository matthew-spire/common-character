package com.matthewspire.commoncharacter.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// This object defines the NetworkModule, which is responsible for setting up the API service.
object NetworkModule {
    // The base URL for the API.
    private const val BASE_URL = "https://api.duckduckgo.com/"

    // A lazy-initialized Moshi instance for handling JSON parsing.
    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // A lazy-initialized HttpLoggingInterceptor for logging HTTP requests and responses.
    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // A lazy-initialized OkHttpClient for making API requests.
    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // A lazy-initialized ApiService for making API calls using Retrofit.
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}