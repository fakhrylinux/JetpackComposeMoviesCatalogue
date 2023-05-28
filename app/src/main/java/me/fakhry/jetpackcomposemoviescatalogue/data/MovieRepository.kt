package me.fakhry.jetpackcomposemoviescatalogue.data

import kotlinx.coroutines.flow.flowOf
import me.fakhry.jetpackcomposemoviescatalogue.model.Movie
import me.fakhry.jetpackcomposemoviescatalogue.model.MovieDataSource


class MovieRepository {

    fun getMovies() = flowOf(MovieDataSource.movies)

    fun getMovieById(id: Long): Movie = MovieDataSource.movies.first { it.id == id }


    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository().apply {
                    instance = this
                }
            }
    }
}