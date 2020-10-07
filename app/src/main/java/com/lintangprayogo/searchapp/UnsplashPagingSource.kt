package com.lintangprayogo.searchapp

import androidx.paging.PagingSource
import com.lintangprayogo.searchapp.data.model.UnsplashResult
import com.lintangprayogo.searchapp.network.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE = 1

class UnsplashPagingSource(private val service: UnsplashApi, private val query: String) :
    PagingSource<Int, UnsplashResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashResult> {
        val page = params.key ?: START_PAGE
        return try {
            val response = service.searchPhoto(query, page, params.loadSize)
            val results = response.results
            LoadResult.Page(
                data = results,
                prevKey = if (page == START_PAGE) null else page - 1,
                nextKey = if (results.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    }

}