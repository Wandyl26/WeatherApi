package com.wandyldiaz.weather.model.domain.service


import android.util.Log
import com.wandyldiaz.weather.model.domain.data.ForeCastResponseData
import javax.inject.Inject
import com.wandyldiaz.weather.model.infrastructure.network.NetworkManager
import com.wandyldiaz.weather.model.infrastructure.utils.JsonTranslator
import com.wandyldiaz.weather.model.domain.data.ResponseData
import org.json.JSONException
import java.net.HttpURLConnection

class ForeCastService @Inject constructor(
    private var networkManager: NetworkManager,
    private var jsonTranslator: JsonTranslator
) {
    private val patch = "v1/forecast.json?key=de5553176da64306b86153651221606&q="
    private val patchDays = "&days=3"
    fun startForeCastService(search: String): ForeCastResponseData {
        return service(search)
    }

    private fun service(foreCast: String): ForeCastResponseData {
        var foreCastResponseData: ForeCastResponseData

        val responseData: ResponseData? = networkManager.getHTTPData(patch + foreCast+ patchDays)
        if (responseData!!.code == HttpURLConnection.HTTP_OK) {
            if(responseData.response.length<3) {
                return ForeCastResponseData(responseData.code, "","",
                    "",null,null,"","",
                    null,null,"","",null,
                    null)
            }
            foreCastResponseData = try {
                jsonTranslator.translateForeCast(responseData.response)
            }catch (e: JSONException) {
                Log.e("ErrorTranslateException", e.message.toString())
                ForeCastResponseData(9, "No se recibio info correctamente","",
                    "",null,null,"","",
                    null,null,"","",null,
                    null)
            }
            if(foreCastResponseData.iconDay0!=null)
                foreCastResponseData.conditionImageDay0=networkManager.imageHTTPData(foreCastResponseData.iconDay0);
            if(foreCastResponseData.iconDay1!=null)
                foreCastResponseData.conditionImageDay1=networkManager.imageHTTPData(foreCastResponseData.iconDay1);
            if(foreCastResponseData.iconDay2!=null)
                foreCastResponseData.conditionImageDay2=networkManager.imageHTTPData(foreCastResponseData.iconDay2);

        } else {
            foreCastResponseData = try {
                val responseTranslate : ResponseData= jsonTranslator.translateError(responseData.response)
                ForeCastResponseData(responseTranslate.code, responseTranslate.response,"",
                    "",null,null,"","",
                    null,null,"","",null,
                    null)
            }catch (e: JSONException) {
                Log.e("ErrorTranslateException", e.message.toString())
                ForeCastResponseData(responseData.code, responseData.response,"",
                    "",null,null,"","",
                    null,null,"","",null,
                    null)
            }
        }
        return foreCastResponseData
    }
}