package com.saintmarina.weather_lookup.forecastList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.saintmarina.weather_lookup.ForecastApi
import com.saintmarina.weather_lookup.R

class ForecastListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val forecast = this.intent.getParcelableExtra<ForecastApi.RootForecast>("forecast")!!
        val listView = findViewById<ListView>(R.id.forecast_list_view)
        val list: ArrayList<ForecastApi.Forecast> = java.util.ArrayList()
        forecast.list.forEach { f -> list.add(f) }
        val arrayAdapter:ArrayAdapter<ForecastApi.Forecast> = ArrayAdapter(this,
            R.layout.list_item, list)
        listView.adapter = arrayAdapter
        Log.i("ForecastListActivity", "forecast: $forecast")
    }
}