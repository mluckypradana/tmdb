package com.luc.base.repository

import com.luc.base.core.api.Resource
import com.luc.base.database.repository.BaseRepository
import com.luc.base.entity.Genre
import com.luc.base.entity.Movie

class MovieRepo : BaseRepository() {
    suspend fun fetchMoviesByGenre(genre: Genre?): Resource<List<Movie>> {
        return try {
            val response = api.fetchMovies(genre?.id)
            if (response.isSuccessful) {
                val body = response.body()
                Resource.Success(body?.results)
            } else Resource.Error()
        } catch (e: Exception) {
            Resource.Error(message = getNoConnectionMessage())
        }
    }
}