package org.robert.project.data


import org.robert.project.AnimeDetailsQuery
import org.robert.project.PopularAnimeQuery
import org.robert.project.model.AnimeDetailResponse
import org.robert.project.model.AnimeListResponse

fun PopularAnimeQuery.Medium.toAnimeListResponse(): AnimeListResponse {
    return AnimeListResponse(
        id = animeList.id,
        title = animeList.title?.english ?: animeList.title?.romaji ?: "",
        coverImage = animeList.coverImage?.extraLarge ?: animeList.coverImage?.large ?: "",
    )
}

fun AnimeDetailsQuery.Media.toAnimeDetailResponse(): AnimeDetailResponse {
    return AnimeDetailResponse(
        id = animeDetail.id,
        title = animeDetail.title?.english ?: animeDetail.title?.romaji ?: "",
        coverImage = animeDetail.coverImage?.extraLarge ?: animeDetail.coverImage?.large ?: "",
        description = animeDetail.description,
        genres = (animeDetail.genres ?: emptyList<String>()) as List<String>
    )
}