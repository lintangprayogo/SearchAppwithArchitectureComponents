package com.lintangprayogo.searchapp.network

import com.google.gson.annotations.SerializedName
import com.lintangprayogo.searchapp.model.UnsplashResult

data class UnsplashResponse(
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<UnsplashResult>
)