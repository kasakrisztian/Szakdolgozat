package com.example.szakdolgozat.data

import com.example.szakdolgozat.data.model.GameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeGamesApi {

    @GET("api/games")
    suspend fun getGames(): List<GameDto>

    @GET("api/game")
    suspend fun getGameDetails(
        @Query("id") id: Int
    ): GameDto
}