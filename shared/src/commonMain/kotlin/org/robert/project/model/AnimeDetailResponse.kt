package org.robert.project.model


data class AnimeDetailResponse(
    val id: Int,
    val title: String,
    val coverImage: String,
    val description: String?,
    val genres: List<String>,
    val bannerImage: String,
    val character: List<Character>?
)

data class Character(
    val name: String,
    val image: String,
    val voiceActor: VoiceActor
)

data class VoiceActor(
    val name: String,
    val image: String,
    val language: String
)