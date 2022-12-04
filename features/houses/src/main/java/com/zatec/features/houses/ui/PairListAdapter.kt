package com.zatec.features.houses.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zatec.features.houses.databinding.PairItemBinding


/**
 * Pair list adapter
 * List adapter that does some boilerplate binding
 * @constructor Create empty Pair list adapter
 */
class PairListAdapter: ListAdapter<Pair<String,String>, PairViewHolder>(pairDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {
        val binding = PairItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PairViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PairViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it)

        }
    }
}
val pairDiffCallback = object : DiffUtil.ItemCallback<Pair<String,String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String,String>,
        newItem: Pair<String,String>
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: Pair<String,String>,
        newItem: Pair<String,String>
    ): Boolean {
        return oldItem == newItem
    }
}

/**
 * Pair view holder
 *
 * @constructor
 * exposes title and info setting through bindTo function, similar to BaseViewHolder with executependingbindings
 * @param binding
 */
class PairViewHolder(binding: PairItemBinding): RecyclerView.ViewHolder(binding.root){
    private val title = binding.title
    private val info = binding.info

    fun bindTo(pair: Pair<String,String>){
        title.text = pair.first
        info.text = pair.second
    }
}
