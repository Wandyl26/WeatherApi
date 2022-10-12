package com.wandyldiaz.weather.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wandyldiaz.weather.model.domain.adapter.SearchAdapterInterface
import com.wandyldiaz.weather.viewmodel.SearchViewModel
import javax.inject.Inject


class SplashActivity : InitActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}