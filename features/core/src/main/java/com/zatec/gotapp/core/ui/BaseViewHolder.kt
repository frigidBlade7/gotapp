package com.zatec.gotapp.core.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base view holder
 * base class for creating viewholders in list adapter
 * @property binding is the databinding layout file of the items
 * @property br is the generated file containing ids of data items to pass to the layout
 * @constructor Create empty Base view holder
 */
open class BaseViewHolder(private val binding: ViewDataBinding, val br: Int) : RecyclerView.ViewHolder(binding.root) {
    /**
     * Bind to
     *
     * @param item
     */
    fun bindTo(item: Any?) {
        binding.setVariable(br, item)
        binding.executePendingBindings()
    }

}