package com.wandyldiaz.weather.model.domain.adapter

import com.wandyldiaz.weather.model.domain.data.ForeCastResponseData

interface ForeCastAdapterInterface {
    fun startForeCastService(search: String?): ForeCastResponseData?
}