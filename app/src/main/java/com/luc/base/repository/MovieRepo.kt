package com.luc.base.repository

import com.luc.base.core.Constant
import com.luc.base.core.retrofit.Resource
import com.luc.base.database.repository.BaseRepository
import com.luc.base.entity.Genre
import com.luc.base.entity.Movie

class MovieRepo : BaseRepository() {
    suspend fun fetchMoviesByGenre(page: Int, genre: Genre?): Resource<MutableList<Movie>> {
        return try {
            val response = api.fetchMovies(page, genre?.id, Constant.POPULARITY)
            if (response.isSuccessful) {
                val body = response.body()
                Resource.Success(body?.results?.toMutableList())
            } else Resource.Error()
        } catch (e: Exception) {
            Resource.Error(message = getGeneralErrorMessage())
        }
    }

    suspend fun fetchMovieDetail(movie: Movie?): Resource<Movie> {
        return try {
            val response = api.fetchMovieDetail(movie?.id, Constant.ADDITIONAL_MOVIE_DATA)
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else Resource.Error()
        } catch (e: Exception) {
            Resource.Error(message = getGeneralErrorMessage())
        }
    }
}