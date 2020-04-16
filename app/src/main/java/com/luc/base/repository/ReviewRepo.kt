package com.luc.base.repository

import com.luc.base.core.retrofit.Resource
import com.luc.base.entity.Movie
import com.luc.base.entity.Review

class ReviewRepo : BaseRepository() {
    suspend fun fetchReviews(page: Int, movie: Movie?): Resource<MutableList<Review>> {
        return try {
            val response = api.fetchReviews( movie?.id, page)
            if (response.isSuccessful) {
                val body = response.body()
                Resource.Success(body?.results?.toMutableList())
            } else Resource.Error()
        } catch (e: Exception) {
            Resource.Error(message = getGeneralErrorMessage())
        }
    }
}