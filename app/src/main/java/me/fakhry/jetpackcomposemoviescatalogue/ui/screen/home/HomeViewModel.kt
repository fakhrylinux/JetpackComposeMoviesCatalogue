package me.fakhry.jetpackcomposemoviescatalogue.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.fakhry.jetpackcomposemoviescatalogue.data.MovieRepository
import me.fakhry.jetpackcomposemoviescatalogue.model.Movie
import me.fakhry.jetpackcomposemoviescatalogue.ui.common.UiState

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Movie>>>
        get() = _uiState

    fun getMovies() {
        viewModelScope.launch {
            repository.getMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { movies ->
                    _uiState.value = UiState.Success(movies)
                }
        }
    }
}