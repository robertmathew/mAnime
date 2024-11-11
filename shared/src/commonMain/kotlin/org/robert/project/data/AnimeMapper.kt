package org.robert.project.data


import org.robert.project.AnimeDetailsQuery
import org.robert.project.PopularAnimeQuery
import org.robert.project.model.AnimeDetailResponse
import org.robert.project.model.AnimeListResponse
import org.robert.project.model.Character
import org.robert.project.model.VoiceActor

fun PopularAnimeQuery.Medium.toAnimeListResponse(): AnimeListResponse {
    return AnimeListResponse(
        id = animeList.id,
        title = animeList.title?.english ?: animeList.title?.romaji ?: "",
        coverImage = animeList.coverImage?.extraLarge ?: animeList.coverImage?.large ?: "",
    )
}

fun AnimeDetailsQuery.Media.toAnimeDetailResponse(): AnimeDetailResponse {
    return AnimeDetailResponse(
        id = id,
        title = title?.english ?: title?.romaji ?: "",
        coverImage = coverImage?.extraLarge ?: coverImage?.large ?: "",
        description = description,
        genres = (genres ?: emptyList<String>()) as List<String>,
        bannerImage = bannerImage ?: "",
        character = characters?.edges?.map {
            Character(
                name = it?.node?.name?.full ?: "",
                image = it?.node?.image?.large ?: "",
                voiceActor = VoiceActor(
                    name = it?.voiceActors?.get(0)?.name?.full ?: "",
                    image = it?.voiceActors?.get(0)?.image?.large ?: "",
                    language = it?.voiceActors?.get(0)?.languageV2 ?: ""
                ),
            )
        }
    )
}