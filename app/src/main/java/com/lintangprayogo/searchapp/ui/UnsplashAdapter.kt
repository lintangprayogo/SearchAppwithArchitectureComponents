package com.lintangprayogo.searchapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.lintangprayogo.searchapp.R
import com.lintangprayogo.searchapp.data.model.UnsplashResult
import com.lintangprayogo.searchapp.databinding.ImageItemBinding

class UnsplashAdapter(private val listener: OnItemUnsplashClickListener) :
    PagingDataAdapter<UnsplashResult, UnsplashAdapter.ResultViewHolder>(
        RESULT_COMPARATOR
    ) {
    inner class ResultViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val result = getItem(position)
                    if (result != null) {
                        listener.onItemClick(result)
                    }
                }

            }
        }

        fun bind(result: UnsplashResult) {
            binding.apply {
                Glide.with(splashImage)
                    .load(result.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_broken_image)
                    .into(splashImage)

                tvUsername.text = result.user.username
            }
        }
    }

    interface OnItemUnsplashClickListener {
        fun onItemClick(result: UnsplashResult)
    }

    companion object {
        private val RESULT_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashResult>() {
            override fun areItemsTheSame(oldItem: UnsplashResult, newItem: UnsplashResult) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: UnsplashResult, newItem: UnsplashResult) =
                oldItem == newItem


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }


}