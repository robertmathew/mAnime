package org.robert.project.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.robert.project.model.AnimeListResponse
import org.robert.project.repository.AnimeRepository

data class AnimeListUiState(
    val popularAnimeList: List<AnimeListResponse?>? = null
)

class AnimeListViewModel: ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(AnimeListUiState())
    val uiState: StateFlow<AnimeListUiState> = _uiState.asStateFlow()

    private val animeRepository: AnimeRepository by inject()

    init {
        fetchPopularAnime()
    }

    private fun fetchPopularAnime() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(popularAnimeList = animeRepository.getPopularAnime())
            }
        }
    }
}