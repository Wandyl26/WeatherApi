package com.wandyldiaz.weather.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wandyldiaz.weather.R
import com.wandyldiaz.weather.model.domain.data.ForeCastResponseData
import java.net.HttpURLConnection.HTTP_OK

class ForeCastActivity : InitActivity() {
    private lateinit var lugar: TextView
    private lateinit var daytemp: TextView
    private lateinit var dayCond: TextView
    private lateinit var dayimageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fore_cast)
        setUpList(intent.extras!!.getString("name")!!)
    }
    private fun setUpList(name: String) {
        lugar = findViewById(R.id.nameid)
        lugar.text=name
        val observer = Observer<ForeCastResponseData>(){foreCastResponse ->
            foreCastResponse(foreCastResponse)
        }

        foreCastViewModel.getResponseForeCast().observe(this, observer)
        foreCastViewModel.startForeCast(name)
    }
    private fun foreCastResponse(foreCastResponse: ForeCastResponseData) {
        if (foreCastResponse.code!= HTTP_OK){
           Toast.makeText(this, foreCastResponse.response, Toast.LENGTH_LONG).show()
            return;
        }

        daytemp = findViewById(R.id.daytemp)
        dayCond = findViewById(R.id.dayCond)
        dayimageView = findViewById(R.id.dayimageView)
        daytemp.text =  daytemp.text.toString()+" " +foreCastResponse.tempDay0.toString() +" C"
        dayCond.text =  dayCond.text.toString()+" " +foreCastResponse.conditionTextDay0
        if(foreCastResponse.conditionImageDay0!=null)
            dayimageView.setImageBitmap(foreCastResponse.conditionImageDay0)


        daytemp = findViewById(R.id.day1temp)
        dayCond = findViewById(R.id.day1Cond)
        dayimageView = findViewById(R.id.day1imageView)
       daytemp.text =  daytemp.text.toString()+" " +foreCastResponse.tempDay1.toString() +" C"
       dayCond.text =  dayCond.text.toString()+" " +foreCastResponse.conditionTextDay1
        if(foreCastResponse.conditionImageDay1!=null)
            dayimageView.setImageBitmap(foreCastResponse.conditionImageDay1)

        daytemp = findViewById(R.id.day2temp)
        dayCond = findViewById(R.id.day2Cond)
        dayimageView = findViewById(R.id.day2imageView)
        daytemp.text =  daytemp.text.toString()+" " +foreCastResponse.tempDay2.toString() +" C"
        dayCond.text =  dayCond.text.toString()+" " +foreCastResponse.conditionTextDay2
        if(foreCastResponse.conditionImageDay2!=null)
            dayimageView.setImageBitmap(foreCastResponse.conditionImageDay2)

    }
}

