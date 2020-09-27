package com.lintangprayogo.searchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.lintangprayogo.searchapp.UnsplashPagingSource
import com.lintangprayogo.searchapp.network.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repo @Inject constructor(private val service: UnsplashApi) {
    fun getSearchResult(query: String) =
        Pager(config = PagingConfig(
            pageSize = 10,
            maxSize = 100,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { UnsplashPagingSource(service, query) }
        ).liveData

}