package com.zatec.gotapp.core.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(private val binding: ViewDataBinding, val br: Int) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(item: Any?) {
        binding.setVariable(br, item)
        binding.executePendingBindings()
    }

}