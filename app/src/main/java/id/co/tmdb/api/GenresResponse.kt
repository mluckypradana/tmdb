package id.co.tmdb.api

import com.google.gson.annotations.SerializedName
import id.co.tmdb.entity.Genre

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
data class GenresResponse (
    @SerializedName("genres") val genres : List<Genre>
)