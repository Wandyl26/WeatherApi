package com.wandyldiaz.weather.model.domain.adapter

import javax.inject.Inject
import com.wandyldiaz.weather.model.domain.service.SearchService
import com.wandyldiaz.weather.model.domain.data.SearchListResponseData

class SearchAdapter @Inject constructor(private var searchService: SearchService) :
    SearchAdapterInterface {
    override fun startSearchService(search: String?): SearchListResponseData? {
        return startService(search)
    }

    private fun startService(search: String?): SearchListResponseData {
        return searchService.startSearchService(search!!)
    }
}