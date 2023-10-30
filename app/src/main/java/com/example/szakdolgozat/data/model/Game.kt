package com.example.szakdolgozat.data.model

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val releaseDate: String
)
