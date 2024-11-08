package org.robert.project.di

import com.apollographql.apollo.ApolloClient
import org.koin.dsl.module
import org.robert.project.network.LoggingApolloInterceptor
import org.robert.project.repository.AnimeRepository

fun appModule() = module {
    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .addInterceptor(LoggingApolloInterceptor())
            .addHttpHeader("Content-Type", "application/json")
            .build()
    }

    single { AnimeRepository(get()) }
}