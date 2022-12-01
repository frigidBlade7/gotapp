package com.zatec.features.characters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zatec.features.characters.databinding.CharacterItemBinding
import com.zatec.features.characters.BR

import com.zatec.gotapp.core.ui.BaseViewHolder

class CharactersListAdapter(
    private val onItemClicked: (book: CharacterUi) -> Unit = {}
) : PagingDataAdapter<CharacterUi, CharacterUiViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: CharacterUiViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterUiViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CharacterUiViewHolder(binding)
        binding.root.setOnClickListener {
            getItem(viewHolder.bindingAdapterPosition)?.let {
                onItemClicked.invoke(it)
            }
        }
        return viewHolder
    }
}

class CharacterUiViewHolder(binding: CharacterItemBinding) :
    BaseViewHolder(binding, BR.item)

val diffCallback = object : DiffUtil.ItemCallback<CharacterUi>() {
    override fun areItemsTheSame(
        oldItem: CharacterUi,
        newItem: CharacterUi
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: CharacterUi,
        newItem: CharacterUi
    ): Boolean {
        return oldItem == newItem
    }

}

