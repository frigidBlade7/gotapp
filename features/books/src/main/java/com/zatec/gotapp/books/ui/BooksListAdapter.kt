package com.zatec.gotapp.books.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zatec.features.books.BR
import com.zatec.features.books.databinding.BookItemBinding
import com.zatec.gotapp.core.ui.BaseViewHolder

class BooksListAdapter(
    private val onItemClicked: (book: BookUi) -> Unit = {}
) : PagingDataAdapter<BookUi, BookUiViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: BookUiViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookUiViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = BookUiViewHolder(binding)
        binding.root.setOnClickListener {
            getItem(viewHolder.bindingAdapterPosition)?.let {
                onItemClicked.invoke(it)
            }
        }
        return viewHolder
    }
}

class BookUiViewHolder(binding: BookItemBinding) :
    BaseViewHolder(binding, BR.item )

val diffCallback = object : DiffUtil.ItemCallback<BookUi>() {
    override fun areItemsTheSame(
        oldItem: BookUi,
        newItem: BookUi
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: BookUi,
        newItem: BookUi
    ): Boolean {
        return oldItem == newItem
    }

}

