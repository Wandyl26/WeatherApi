package com.wandyldiaz.weather.model.infrastructure.injection

import android.app.Application
import com.wandyldiaz.weather.model.domain.adapter.ForeCastAdapterInterface
import javax.inject.Singleton
import com.wandyldiaz.weather.model.infrastructure.network.NetworkManager
import com.wandyldiaz.weather.model.infrastructure.utils.JsonTranslator
import com.wandyldiaz.weather.model.domain.adapter.SearchAdapterInterface
import com.wandyldiaz.weather.view.InitActivity
import com.wandyldiaz.weather.viewmodel.ForeCastViewModel
import com.wandyldiaz.weather.viewmodel.SearchViewModel
import dagger.Component

@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(initActivity: InitActivity?)
    fun networkManager(): NetworkManager?
    fun jsonTranslator(): JsonTranslator?
    fun searchAdapterInterface(): SearchAdapterInterface?
    fun ForeCastAdapterInterface(): ForeCastAdapterInterface?
    fun searchViewModel(): SearchViewModel?
    fun foreCastViewModel(): ForeCastViewModel?

}