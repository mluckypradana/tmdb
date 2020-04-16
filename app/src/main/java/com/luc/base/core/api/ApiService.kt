package com.luc.base.core.api

import com.luc.base.api.BaseListResponse
import com.luc.base.api.GenresResponse
import com.luc.base.entity.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun fetchCategories(): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("with_genres") withGenres: Int?
    ): Response<BaseListResponse<Movie>>
}