package id.co.tmdb.repository

import id.co.tmdb.core.Constant
import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.entity.Genre
import java.lang.Exception

class GenreRepository : BaseRepository() {
    suspend fun fetchGenres(): Resource<List<Genre>> {
        return try {
            val response = api.fetchCategories()
            if (response.isSuccessful) {
                val body = response.body()
                saveGenres(body?.genres)
                Resource.Success(body?.genres)
            } else Resource.Error()
        } catch (e: Exception) {
            Resource.Error(message = getGeneralErrorMessage())
        }
    }

    private fun saveGenres(body: List<Genre>?) { sessionStorage.put(Constant.GENRES, body) }

    fun getGenres(): List<Genre> {
        return sessionStorage.get(Constant.GENRES) ?: mutableListOf()
    }
}