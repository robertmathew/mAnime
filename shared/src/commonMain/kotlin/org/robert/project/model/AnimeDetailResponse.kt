package org.robert.project.model

data class AnimeDetailResponse(
    val id: Int,
    val title: String,
    val coverImage: String,
    val description: String?,
    val genres: List<String>,
)