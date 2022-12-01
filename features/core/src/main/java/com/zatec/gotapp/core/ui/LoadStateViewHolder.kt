package com.zatec.gotapp.core.ui

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.zatec.features.core.databinding.LoadStateItemBinding

class LoadStateViewHolder(binding: LoadStateItemBinding): RecyclerView.ViewHolder(binding.root) {
    private val progressBar: LinearProgressIndicator = binding.linearProgressIndicator

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
    }

}