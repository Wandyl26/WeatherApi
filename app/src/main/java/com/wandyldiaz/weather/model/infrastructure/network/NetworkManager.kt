package com.wandyldiaz.weather.model.infrastructure.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.NetworkOnMainThreadException
import android.util.Log
import com.wandyldiaz.weather.model.domain.data.ResponseData
import java.io.*
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

/**
 * Created by Wandyl Diaz
 */
class NetworkManager {
    private val urlApi = "https://api.weatherapi.com/"
    private lateinit var responseData: ResponseData

    fun getHTTPData(requestUrl: String?): ResponseData? {
        val url: URL
        var response: String? = ""
        responseData= ResponseData(0,"")
        try {
            url = URL(urlApi+requestUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            val responseCode = conn.responseCode
            var line: String?
            var br: BufferedReader?

            if (conn.responseCode == HTTP_OK)
                 br = BufferedReader(InputStreamReader(conn.inputStream))
            else
                br = BufferedReader(InputStreamReader(conn.errorStream))

            while (br.readLine().also { line = it } != null) response += line
            responseData= ResponseData(responseCode, response.toString())

        } catch (e: ProtocolException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(5, "No se encontro")
        } catch (e: MalformedURLException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(6, "Error de conexion")
        } catch (e: IOException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(7, "Error de conexion")
        }catch (e: NetworkOnMainThreadException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(8, "Error de conexion")
        }
        return responseData
    }

    fun imageHTTPData(requestUrl: String?): Bitmap? {
        var image: Bitmap? = null
        try {
            val imageURL = "https:$requestUrl"
            val `in` = java.net.URL(imageURL).openStream()
            image = BitmapFactory.decodeStream(`in`)
        }
         catch (e: Exception) {
             Log.e("ErrorImage", e.message.toString())
         }
        return image;
    }

    fun postHTTPData(requestUrl: String, comment: String): ResponseData? {
        val url: URL
        var responseCode: Int
        var response: String? = ""
        var conn: HttpURLConnection? = null
        responseData= ResponseData(0,"")
        try {
            url = URL(urlApi + requestUrl)
            conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.doInput = true
            conn.setFixedLengthStreamingMode(comment.toByteArray().size)
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            conn.setRequestProperty("Accept", "application/json")

            val out: OutputStream = BufferedOutputStream(conn.outputStream)
            out.write(comment.toByteArray())
            out.flush()
            responseCode = conn.responseCode
            out.close()
            var line: String?
            val br = BufferedReader(
                InputStreamReader(
                    conn.inputStream
                )
            )
            while (br.readLine().also { line = it } != null) response += line
            responseData= ResponseData(responseCode, response.toString())
            Log.d("messaje", response!!)
        } catch (e: ProtocolException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(5, "Error de conexion")
        } catch (e: MalformedURLException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(6, "Error de conexion")
        } catch (e: IOException) {
            Log.e("Error", e.message.toString())
            responseData= ResponseData(7, "Error de conexion")
        } finally {
            conn?.disconnect()
        }
        return responseData
    }

}