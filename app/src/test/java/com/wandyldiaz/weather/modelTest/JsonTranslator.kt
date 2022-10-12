package com.wandyldiaz.weather.modelTest

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JsonTranslator {
    var gson = Gson()
    fun translate(`object`: Any?): String {
        return gson.toJson(`object`)
    }

    fun translate(json: String?, chase: Class<*>?): Any {
        return gson.fromJson(json, chase)
    }

    @Throws(JSONException::class)
    fun translateSearchList(json: String): SearchListResponseData {
        val jsonArray: JSONArray = JSONArray(json)
        val searchListResponseData= SearchListResponseData(200,"", emptyList())
        val jsonObject: Array<JSONObject?> = arrayOfNulls(jsonArray.length())
        val searchResponseDataList: MutableList<SearchResponseData> = mutableListOf()
        for (i in 0 until jsonArray.length()) {
            jsonObject[i] = jsonArray.getJSONObject(i)
            val id = jsonObject[i]?.getInt("id")
            val name = jsonObject[i]?.getString("name")
            val country = jsonObject[i]?.getString("country")
            searchResponseDataList.add(SearchResponseData(id, name, country))
        }
        searchListResponseData.searchResponseDataList=searchResponseDataList;
        return searchListResponseData;
    }

    @Throws(JSONException::class)
    fun translateForeCast(json: String): ForeCastResponseData {
        val jsonResponse = JSONObject(json)
        val foreCastDayJsonArray: JSONArray
        var dayJson: JSONObject
        var conditionJson: JSONObject
        val foreCastResponseData= ForeCastResponseData(200, "","",
            "",null,null,"","",
            null,null,"","",null,
            null)

        val foreCastJson: JSONObject = JSONObject(jsonResponse.getString("forecast"))
        foreCastDayJsonArray = JSONArray(foreCastJson.getString("forecastday"))

        val jsonObject: Array<JSONObject?> = arrayOfNulls(foreCastDayJsonArray.length())
        for (i in 0 until foreCastDayJsonArray.length()) {
            jsonObject[i] =  foreCastDayJsonArray.getJSONObject(i)
            dayJson =  JSONObject(jsonObject[i]?.getString("day").toString())
            conditionJson =  JSONObject(dayJson.getString("condition"))
            when (i) {
                0 -> {
                    foreCastResponseData.tempDay0 = dayJson.getDouble("avgtemp_c")
                    foreCastResponseData.conditionTextDay0 = conditionJson.getString("text")
                    foreCastResponseData.iconDay0 = conditionJson.getString("icon")
                }
                1 -> {
                    foreCastResponseData.tempDay1 = dayJson.getDouble("avgtemp_c")
                    foreCastResponseData.conditionTextDay1 = conditionJson.getString("text")
                    foreCastResponseData.iconDay1 = conditionJson.getString("icon")
                }
                else -> {
                    foreCastResponseData.tempDay2 =  dayJson.getDouble("avgtemp_c")
                    foreCastResponseData.conditionTextDay2 =  conditionJson.getString("text")
                    foreCastResponseData.iconDay2 = conditionJson.getString("icon")
                }
            }
        }

        return foreCastResponseData;
    }

    @Throws(JSONException::class)
    fun translateError(json: String): ResponseData {
        val jsonResponse: JSONObject = JSONObject(json)
        val responseData= ResponseData(9999, "")

        val errorJson: JSONObject = JSONObject(jsonResponse.getString("error"))

        responseData.code = errorJson.getInt("code")
        responseData.response = errorJson.getString("message")

        return responseData;
    }

}