package id.co.tmdb.api

import com.google.gson.annotations.SerializedName

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
data class BaseListResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)