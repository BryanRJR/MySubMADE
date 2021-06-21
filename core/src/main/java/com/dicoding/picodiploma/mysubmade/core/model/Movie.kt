package com.dicoding.picodiploma.mysubmade.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val coverMovie: String,
    val title: String,
    val date: String,
    val vote_average: Double,
    val description: String,
    val isFavorite: Boolean
): Parcelable