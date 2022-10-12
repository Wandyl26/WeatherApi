package com.wandyldiaz.weather.model.domain.adapter

import com.wandyldiaz.weather.model.domain.data.SearchListResponseData

interface SearchAdapterInterface {
    fun startSearchService(search: String?): SearchListResponseData?
}