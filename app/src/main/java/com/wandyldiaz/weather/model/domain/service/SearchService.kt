package com.wandyldiaz.weather.model.domain.service


import android.util.Log
import javax.inject.Inject
import com.wandyldiaz.weather.model.infrastructure.network.NetworkManager
import com.wandyldiaz.weather.model.infrastructure.utils.JsonTranslator
import com.wandyldiaz.weather.model.domain.data.SearchListResponseData
import com.wandyldiaz.weather.model.domain.data.ResponseData
import org.json.JSONException
import java.net.HttpURLConnection

class SearchService @Inject constructor(
    private var networkManager: NetworkManager,
    private var jsonTranslator: JsonTranslator
) {
    private val patch = "v1/search.json?key=de5553176da64306b86153651221606&q="
    fun startSearchService(search: String): SearchListResponseData {
        return service(search)
    }

    private fun service(search: String): SearchListResponseData {
        var searchListResponseData: SearchListResponseData
        val responseData: ResponseData? = networkManager.getHTTPData(patch + search)
        if (responseData!!.code == HttpURLConnection.HTTP_OK) {
            if(responseData.response.length<3) {
                return SearchListResponseData(responseData.code, "", emptyList())
            }
            try {
                searchListResponseData = jsonTranslator.translateSearchList(responseData.response)

                searchListResponseData = SearchListResponseData(
                    responseData.code,
                    "",
                    searchListResponseData.searchResponseDataList)
            }catch (e: JSONException) {
                Log.e("ErrorTranslateException", e.message.toString())
                searchListResponseData = SearchListResponseData(9, "No se recibio info correctamente", emptyList())
            }

        } else {
            searchListResponseData = try {
                val responseTranslate : ResponseData= jsonTranslator.translateError(responseData.response)
                   SearchListResponseData(responseTranslate.code, responseTranslate.response, emptyList())
            }catch (e: JSONException) {
                Log.e("ErrorTranslateException", e.message.toString())
                SearchListResponseData(responseData.code, responseData.response, emptyList())
            }

        }
        return searchListResponseData
    }
}