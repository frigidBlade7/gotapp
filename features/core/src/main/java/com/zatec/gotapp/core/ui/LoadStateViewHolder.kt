package com.zatec.gotapp.core.ui

import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.zatec.features.core.databinding.LoadStateItemBinding

/**
 * Load state view holder
 *
 * @constructor
 * viewholder object to show the loading state in the recycler view
 * @param binding is the layout of the loader view
 */
class LoadStateViewHolder(binding: LoadStateItemBinding): RecyclerView.ViewHolder(binding.root) {
    private val progressBar: LinearProgressIndicator = binding.linearProgressIndicator
    private val error: TextView = binding.textView

    /**
     * Bind
     * function to setup the current loadstate with the current ui component in the item layout
     * @param loadState
     */
    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        error.isVisible = loadState is LoadState.Error
    }

}