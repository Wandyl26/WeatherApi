package com.wandyldiaz.weather.model.infrastructure.injection

import com.wandyldiaz.weather.model.domain.adapter.ForeCastAdapter
import com.wandyldiaz.weather.model.domain.adapter.ForeCastAdapterInterface
import javax.inject.Singleton
import com.wandyldiaz.weather.model.infrastructure.network.NetworkManager
import com.wandyldiaz.weather.model.infrastructure.utils.JsonTranslator
import com.wandyldiaz.weather.model.domain.service.SearchService
import com.wandyldiaz.weather.model.domain.adapter.SearchAdapterInterface
import com.wandyldiaz.weather.model.domain.adapter.SearchAdapter
import com.wandyldiaz.weather.model.domain.service.ForeCastService
import com.wandyldiaz.weather.viewmodel.ForeCastViewModel
import com.wandyldiaz.weather.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
    @Singleton
    @Provides
    fun networkManager(): NetworkManager {
        return NetworkManager()
    }

    @Singleton
    @Provides
    fun jsonTranslator(): JsonTranslator {
        return JsonTranslator()
    }

    @Singleton
    @Provides
    fun searchAdapterInterface(searchService: SearchService?): SearchAdapterInterface {
        return SearchAdapter(searchService!!)
    }

    @Singleton
    @Provides
    fun foreCastAdapterInterface(forecastService: ForeCastService?): ForeCastAdapterInterface {
        return ForeCastAdapter(forecastService!!)
    }

    @Singleton
    @Provides
    fun searchViewModel(searchAdapterInterface: SearchAdapterInterface?): SearchViewModel {
        return SearchViewModel(searchAdapterInterface!!)
    }
    @Singleton
    @Provides
    fun foreCastViewModel(foreCastAdapterInterface: ForeCastAdapterInterface?): ForeCastViewModel {
        return ForeCastViewModel(foreCastAdapterInterface!!)
    }
}