package com.wandyldiaz.weather.model.domain.adapter

import com.wandyldiaz.weather.model.domain.data.ForeCastResponseData
import com.wandyldiaz.weather.model.domain.service.ForeCastService
import javax.inject.Inject

class ForeCastAdapter  @Inject constructor(private var foreCastService: ForeCastService) :
    ForeCastAdapterInterface {
    override fun startForeCastService(search: String?): ForeCastResponseData? {
        return startService(search)
    }

    private fun startService(search: String?): ForeCastResponseData {
        return foreCastService.startForeCastService(search!!)
    }
}