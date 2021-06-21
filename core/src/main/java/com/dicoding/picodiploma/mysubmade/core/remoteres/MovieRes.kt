package com.dicoding.picodiploma.mysubmade.core.remoteres

import com.google.gson.annotations.SerializedName

data class MovieRes(
    val id: Int,

    @SerializedName("poster_path")
    val coverMovie: String,
    val title: String,

    @SerializedName("release_date")
    val date: String,
    val vote_average: Double,

    @SerializedName("overview")
    val desc: String
)