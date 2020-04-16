package id.co.tmdb.repository

import id.co.tmdb.core.retrofit.Resource
import id.co.tmdb.entity.Movie
import id.co.tmdb.entity.Review

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