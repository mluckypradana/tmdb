package com.luc.base.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
data class Genre(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)