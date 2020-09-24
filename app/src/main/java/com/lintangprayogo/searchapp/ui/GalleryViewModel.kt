package com.lintangprayogo.searchapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.lintangprayogo.searchapp.network.Repo

class GalleryViewModel  @ViewModelInject constructor(private val repo:Repo):ViewModel() {
}