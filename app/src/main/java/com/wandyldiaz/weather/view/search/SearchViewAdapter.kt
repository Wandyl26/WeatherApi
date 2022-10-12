package com.wandyldiaz.weather.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wandyldiaz.weather.R
import com.wandyldiaz.weather.model.domain.data.SearchResponseData

class SearchViewAdapter(private var interfaceClick: SearchInterfaceOnClick) : ListAdapter<SearchResponseData, SearchViewAdapter.SearchViewHolder>(SearchViewAdapterCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            this.interfaceClick = interfaceClick
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.search_item, parent, false)
            return SearchViewHolder(view)
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            val expense = getItem(position)
            holder.bind(expense, this.interfaceClick)
        }

        class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val name: TextView = view.findViewById(R.id.name)
            private val country: TextView = view.findViewById(R.id.country)
            private val item: LinearLayout = view.findViewById(R.id.item)

            fun bind(searchData: SearchResponseData, interfaceClick: SearchInterfaceOnClick) {
                name.text = searchData.name
                country.text = searchData.country
                item.setOnClickListener {
                    interfaceClick.foreCastClick(name.text as String)
                }
            }
        }
}