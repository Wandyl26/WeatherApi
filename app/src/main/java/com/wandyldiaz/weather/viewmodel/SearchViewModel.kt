package com.wandyldiaz.weather.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wandyldiaz.weather.model.domain.adapter.SearchAdapterInterface
import com.wandyldiaz.weather.model.domain.data.SearchListResponseData
import java.util.concurrent.Executors
import javax.inject.Inject

 class SearchViewModel @Inject constructor(
   private var searchAdapterInterface: SearchAdapterInterface
): ViewModel() {
    private  val searchResult= MutableLiveData<SearchListResponseData>()

     fun getResponseSearch(): LiveData<SearchListResponseData> {
         return searchResult
     }
    fun startSearch (search: String){
        startService(search)
    }
    private fun startService(search: String) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var searchListResponseData: SearchListResponseData

        executor.execute {
            searchListResponseData=(searchAdapterInterface.startSearchService(search)!!)
            handler.post {
                searchResult.value=searchListResponseData
            }
        }
    }
}