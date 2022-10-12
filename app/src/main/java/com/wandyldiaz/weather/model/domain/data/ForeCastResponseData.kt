package com.wandyldiaz.weather.model.domain.data

import android.graphics.Bitmap

data class ForeCastResponseData(
    val code: Int,
    val response: String,
    var conditionTextDay0: String?,
    var iconDay0: String?,
    var tempDay0: Double?,
    var conditionImageDay0: Bitmap?,
    var conditionTextDay1: String?,
    var iconDay1: String?,
    var tempDay1: Double?,
    var conditionImageDay1: Bitmap?,
    var conditionTextDay2: String?,
    var iconDay2: String?,
    var tempDay2: Double?,
    var conditionImageDay2: Bitmap?
)
