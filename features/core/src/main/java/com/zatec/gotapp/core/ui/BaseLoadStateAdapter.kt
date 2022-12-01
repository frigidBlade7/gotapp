package com.zatec.gotapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.zatec.features.core.databinding.LoadStateItemBinding

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

