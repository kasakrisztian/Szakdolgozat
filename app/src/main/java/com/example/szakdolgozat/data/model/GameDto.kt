package com.example.szakdolgozat.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class GameDto(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerialName("short_description")
    val shortDescription: String,
    val description: String? = null,
    val genre: String,
    val platform: String,
    val publisher: String,
    @SerialName("release_date")
    val releaseDate: String
)

fun GameDto.toGame() = Game(
    id = id,
    title = title,
    thumbnail = thumbnail,
    shortDescription = shortDescription,
    genre = genre,
    platform = platform,
    publisher = publisher,
    releaseDate = releaseDate
)