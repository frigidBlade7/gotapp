package com.zatec.features.houses.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zatec.features.houses.BR
import com.zatec.features.houses.databinding.HouseItemBinding
import com.zatec.gotapp.core.ui.BaseViewHolder

/**
 * Houses list adapter
 *
 * @property onItemClicked function to execute when an item is tapped
 * @constructor Create empty Houses list adapter
 */
class HousesListAdapter(
    private val onItemClicked: (book: HouseUi) -> Unit = {}
) : PagingDataAdapter<HouseUi, HouseUiViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: HouseUiViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HouseUiViewHolder {
        val binding = HouseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = HouseUiViewHolder(binding)
        binding.root.setOnClickListener {
            getItem(viewHolder.bindingAdapterPosition)?.let {
                onItemClicked.invoke(it)
            }
        }
        return viewHolder
    }
}

/**
 * House ui view holder
 *
 * @constructor
 *
 * @param binding databinding layout of the
 */
class HouseUiViewHolder(binding: HouseItemBinding) :
    BaseViewHolder(binding, BR.item)

val diffCallback = object : DiffUtil.ItemCallback<HouseUi>() {
    override fun areItemsTheSame(
        oldItem: HouseUi,
        newItem: HouseUi
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: HouseUi,
        newItem: HouseUi
    ): Boolean {
        return oldItem == newItem
    }

}

