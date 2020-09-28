package com.lintangprayogo.searchapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lintangprayogo.searchapp.data.Repo

class GalleryViewModel @ViewModelInject constructor(private val repo: Repo) : ViewModel() {
    private val curentQuery = MutableLiveData(DEFAULT_QUERY)

    fun results() = curentQuery.switchMap { query ->
        repo.getSearchResult(query).cachedIn(viewModelScope)
    }


    fun searchResults(query: String) {
        curentQuery.value = query
    }

    companion object {
        const val DEFAULT_QUERY = "cup of tea"
    }
}