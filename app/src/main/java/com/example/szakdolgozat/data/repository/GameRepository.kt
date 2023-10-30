package com.example.szakdolgozat.data.repository

import com.example.szakdolgozat.data.NetworkModule
import com.example.szakdolgozat.data.model.Game
import com.example.szakdolgozat.data.model.toGame

class GameRepository {

    private val api = NetworkModule.provideFreeGamesApi()

    private val games = mutableListOf<Game>()

    suspend fun getGames(): List<Game> {
        games.addAll(api.getGames().map {
            it.toGame()
        })
        return games
    }

    suspend fun getGameDetail(gameId: Int): Game {
        return api.getGameDetails(gameId).toGame()
    }

    fun search(query: String): List<Game> {
        return when {
            query.isBlank() -> games
            else -> games.filter { game ->
                game.title.contains(other = query.trim(), ignoreCase = true)
            }
        }
    }
}