package me.fakhry.jetpackcomposemoviescatalogue.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.fakhry.jetpackcomposemoviescatalogue.data.MovieRepository
import me.fakhry.jetpackcomposemoviescatalogue.model.Movie
import me.fakhry.jetpackcomposemoviescatalogue.ui.common.UiState

class DetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Movie>>
        get() = _uiState

    fun getMovieById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMovieById(id))
        }
    }
}