package com.luc.base.core.api

import com.luc.base.api.BaseListResponse
import com.luc.base.api.GenresResponse
import com.luc.base.entity.Movie
import com.luc.base.entity.Review
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun fetchCategories(): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("page") page: Int,
        @Query("with_genres") withGenres: Int?,
        @Query("sort_by") sortBy: String?
    ): Response<BaseListResponse<Movie>>

    @GET("movie/{id}/reviews")
    suspend fun fetchReviews(
        @Path("id") id: Int?,
        @Query("page") page: Int
    ): Response<BaseListResponse<Review>>

    @GET("movie/{id}")
    suspend fun fetchMovieDetail(
        @Path("id") id: Int?,
        @Query("append_to_response") appendToResponse: String?
    ): Response<Movie>
}