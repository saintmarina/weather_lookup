package com.saintmarina.weather_lookup.forecastList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.saintmarina.weather_lookup.ForecastApi
import com.saintmarina.weather_lookup.R
import com.saintmarina.weather_lookup.Util

class ForecastDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_detail)
        val forecast = this.intent.getParcelableExtra<ForecastApi.Forecast>("forecast")!!
        val city = this.intent.getStringExtra("city")!!
        title = city
        val temperature = findViewById<TextView>(R.id.temperature_large)
        val temperatureFeelsLike = findViewById<TextView>(R.id.temperature_feels_like)
        val weather = findViewById<TextView>(R.id.weather)
        val weatherDescription = findViewById<TextView>(R.id.weather_description)

        temperature.text = Util.formattedTemperature(forecast.main.temp)
        temperatureFeelsLike.text = getString(R.string.feels_like_text, Util.formattedTemperature(forecast.main.temp))
        weather.text = forecast.weather[0].main
        weatherDescription.text = forecast.weather[0].description
    }
}