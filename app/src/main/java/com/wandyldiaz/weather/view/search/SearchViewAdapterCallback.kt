package com.wandyldiaz.weather.view.search

import androidx.recyclerview.widget.DiffUtil
import com.wandyldiaz.weather.model.domain.data.SearchResponseData

class SearchViewAdapterCallback : DiffUtil.ItemCallback<SearchResponseData>() {
    override fun areItemsTheSame(oldItem: SearchResponseData, newItem: SearchResponseData): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: SearchResponseData, newItem: SearchResponseData): Boolean {
        return oldItem == newItem
    }
}