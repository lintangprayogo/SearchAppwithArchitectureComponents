package com.lintangprayogo.searchapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashResult(
    val id: String,
    val alt_description: String,
    val description: String,
    val likes: Int,
    val urls: UnsplashUrls,
    val user: User
) : Parcelable