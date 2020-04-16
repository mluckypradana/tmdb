package com.luc.base.repository

import com.luc.base.core.Constant
import com.luc.base.core.api.Resource
import com.luc.base.database.repository.BaseRepository
import com.luc.base.entity.Genre
import java.lang.Exception

class GenreRepo : BaseRepository() {
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