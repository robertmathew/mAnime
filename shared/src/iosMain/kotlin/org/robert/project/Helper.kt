package org.robert.project

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.robert.project.di.appModule
import org.robert.project.repository.AnimeRepository

class DiHelper : KoinComponent {
    private val repository : AnimeRepository by inject()
    fun getAnimeRepository(): AnimeRepository = repository
}

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}