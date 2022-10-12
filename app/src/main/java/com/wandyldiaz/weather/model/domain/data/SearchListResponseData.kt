package com.wandyldiaz.weather.model.domain.data

data class SearchListResponseData(
    val code: Int,
    val response: String,
    var searchResponseDataList: List<SearchResponseData>
)
