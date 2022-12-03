package com.zatec.gotapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.zatec.features.core.databinding.LoadStateItemBinding

/**
 * Base load state adapter
 * Adapter to show loading state of items in a recycler view
 * @constructor Create empty Base load state adapter
 */
class BaseLoadStateAdapter(
) : LoadStateAdapter<LoadStateViewHolder>() {


    override fun onBindViewHolder(holder: LoadStateViewHolder,
                                  loadState: LoadState) = holder.bind(loadState)


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding: LoadStateItemBinding = LoadStateItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LoadStateViewHolder(binding)
    }
}

