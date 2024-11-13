package org.robert.project.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import org.robert.project.AnimeDetailsQuery

import org.robert.project.PopularAnimeQuery
import org.robert.project.data.toAnimeDetailResponse
import org.robert.project.data.toAnimeListResponse
import org.robert.project.model.AnimeDetailResponse
import org.robert.project.model.AnimeListResponse
import org.robert.project.type.MediaSort
import org.robert.project.type.MediaType
import org.robert.project.type.StaffLanguage

interface AnimeRepositoryInterface {
    suspend fun getPopularAnime(): List<AnimeListResponse?>?
    suspend fun getAnimeDetails(animeId: Int): AnimeDetailResponse?
}

class AnimeRepository(private val mApolloClient: ApolloClient) : AnimeRepositoryInterface {

    override suspend fun getPopularAnime(): List<AnimeListResponse?>? {
        return mApolloClient.query(
                PopularAnimeQuery(
                    type = Optional.present(MediaType.ANIME),
                    page = Optional.present(1),
                    perPage = Optional.present(50),
                    sort = Optional.present(listOf( MediaSort.SCORE_DESC))
                )
            ).execute().data?.page?.media?.map { it?.toAnimeListResponse()}
    }

    override suspend fun getAnimeDetails(animeId: Int): AnimeDetailResponse? {
        return mApolloClient.query(
            AnimeDetailsQuery(
                mediaId = Optional.present(animeId),
                voicePerPage = Optional.present(6),
                language = Optional.present(StaffLanguage.JAPANESE)
            )
        ).execute().data?.Media?.toAnimeDetailResponse()
    }
}