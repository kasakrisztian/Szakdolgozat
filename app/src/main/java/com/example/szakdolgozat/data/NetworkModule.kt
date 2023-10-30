package com.example.szakdolgozat.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create

object NetworkModule {

    private fun provideOkHttpClient() = OkHttpClient.Builder().build()

    private fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    private fun provideConverterFactory(): Converter.Factory =
        provideJson().asConverterFactory("application/json".toMediaType())

    private fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl("https://www.freetogame.com/")
        .addConverterFactory(provideConverterFactory())
        .build()

    fun provideFreeGamesApi(): FreeGamesApi = provideRetrofit().create()
}