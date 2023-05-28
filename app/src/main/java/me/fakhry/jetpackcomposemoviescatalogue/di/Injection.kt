package me.fakhry.jetpackcomposemoviescatalogue.di

import me.fakhry.jetpackcomposemoviescatalogue.data.MovieRepository

object Injection {

    fun provideRepository(): MovieRepository {
        return MovieRepository.getInstance()
    }
}