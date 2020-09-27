package com.lintangprayogo.searchapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lintangprayogo.searchapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery){
    private val viewModel by viewModels<GalleryViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.results().observe(viewLifecycleOwner){

        }
    }
}