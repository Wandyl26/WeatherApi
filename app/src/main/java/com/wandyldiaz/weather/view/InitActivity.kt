package com.wandyldiaz.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wandyldiaz.weather.model.infrastructure.injection.AppModule
import com.wandyldiaz.weather.model.infrastructure.injection.DaggerAppComponent
import com.wandyldiaz.weather.model.infrastructure.injection.RoomModule
import com.wandyldiaz.weather.viewmodel.ForeCastViewModel
import com.wandyldiaz.weather.viewmodel.SearchViewModel
import javax.inject.Inject

open class InitActivity : AppCompatActivity() {
    @Inject
    lateinit var searchViewModel: SearchViewModel
    @Inject
    lateinit var foreCastViewModel: ForeCastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().build().inject(this)

    }
}