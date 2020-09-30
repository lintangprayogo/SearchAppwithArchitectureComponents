package com.lintangprayogo.searchapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintangprayogo.searchapp.R
import com.lintangprayogo.searchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()

    private var _binding:FragmentGalleryBinding? =null
    private  val binding get()=_binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryBinding.bind(view)

        val adapter =UnsplashAdapter()

        binding.apply {
            rvSplash.setHasFixedSize(true)
            rvSplash.adapter=adapter.withLoadStateHeaderAndFooter(
                header= UnsplashResultLoadStateAdapter{adapter.retry()},
                footer = UnsplashResultLoadStateAdapter{adapter.retry()}
            )
        }

        viewModel.results().observe(viewLifecycleOwner) {
              adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}