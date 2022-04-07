package com.saintmarina.weather_lookup.forecastList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.saintmarina.weather_lookup.ForecastApi.Forecast
import com.saintmarina.weather_lookup.ForecastApi.RootForecast
import com.saintmarina.weather_lookup.R

class ForecastListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val rootForecast = this.intent.getParcelableExtra<RootForecast>("rootForecast")!!
        val listView = findViewById<ListView>(R.id.forecast_list_view)

        title = rootForecast.city.name
        val onClick = { forecast: Forecast ->
                val intent: Intent = Intent(this, ForecastDetailActivity::class.java)
                    .apply {
                        putExtra("forecast", forecast)
                        putExtra("city", rootForecast.city.name)
                    }
                startActivity(intent)
        }
        listView.adapter = ForecastAdapter(this, R.layout.forecast_list_item, onClick)
            .apply { addAll(rootForecast.list) }
    }
}