package org.robert.project.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.robert.project.model.AnimeDetailResponse
import org.robert.project.repository.AnimeRepository

data class AnimeDetailUiState(
    val animeDetail: AnimeDetailResponse? = null
)

class AnimeDetailViewModel : ViewModel(), KoinComponent {
    private val _uiState = MutableStateFlow(AnimeDetailUiState())
    val uiState: StateFlow<AnimeDetailUiState> = _uiState.asStateFlow()

    private val animeRepository: AnimeRepository by inject()

    fun fetchPopularAnime(animeId: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(animeDetail = animeRepository.getAnimeDetails(animeId = animeId))
            }
        }
    }
}