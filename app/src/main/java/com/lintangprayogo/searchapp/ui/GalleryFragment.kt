package com.lintangprayogo.searchapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.paging.LoadState
import com.lintangprayogo.searchapp.R
import com.lintangprayogo.searchapp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryBinding.bind(view)

        val adapter = UnsplashAdapter()

        binding.apply {
            rvSplash.setHasFixedSize(true)
            rvSplash.itemAnimator = null
            rvSplash.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UnsplashResultLoadStateAdapter { adapter.retry() },
                footer = UnsplashResultLoadStateAdapter { adapter.retry() },
            )
            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.results().observe(viewLifecycleOwner) {
            Log.d("TAG", "onViewCreated: ")
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadstate ->
                binding.apply {
                    progressBar.isVisible = loadstate.source.refresh is LoadState.Loading
                    rvSplash.isVisible = loadstate.source.refresh is LoadState.NotLoading
                    btnRetry.isVisible = loadstate.source.refresh is LoadState.Error
                    tvError.isVisible = loadstate.source.refresh is LoadState.Error


                    if (loadstate.source.refresh is LoadState.NotLoading && loadstate.append.endOfPaginationReached && adapter.itemCount<1){
                        rvSplash.isVisible = false
                        tvEmpty.isVisible  =true
                    }else {
                        tvEmpty.isVisible  =false
                    }

                }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_gallery, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    binding.rvSplash.scrollToPosition(0)
                    viewModel.searchResults(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}