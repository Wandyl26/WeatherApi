package com.wandyldiaz.weather.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wandyldiaz.weather.model.domain.adapter.ForeCastAdapterInterface
import com.wandyldiaz.weather.model.domain.data.ForeCastResponseData
import java.util.concurrent.Executors
import javax.inject.Inject

 class ForeCastViewModel @Inject constructor(
   private var foreCastAdapterInterface: ForeCastAdapterInterface
): ViewModel() {
    private  val foreCastResult= MutableLiveData<ForeCastResponseData>()

     fun getResponseForeCast(): LiveData<ForeCastResponseData> {
         return foreCastResult
     }
    fun startForeCast (search: String){
        startService(search)
    }
    private fun startService(search: String) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var foreCastListResponseData: ForeCastResponseData

        executor.execute {
            foreCastListResponseData=(foreCastAdapterInterface.startForeCastService(search)!!)
            handler.post {
                foreCastResult.value=foreCastListResponseData
            }
        }
    }
}