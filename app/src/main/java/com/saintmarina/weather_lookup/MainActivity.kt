package com.saintmarina.weather_lookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.saintmarina.weather_lookup.forecastList.ForecastListActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val coroutine = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val input = findViewById<EditText>(R.id.input)
        val button = findViewById<Button>(R.id.lookup)
        val api = ForecastApi()

        button.setOnClickListener{
            coroutine.launch {
                val forecast = api.getForecast("miami")
                Log.i("Main", "forecast: $forecast")
                //"${forecast.list[0].weather[0].main}, temp: ${forecast.list[0].main.temp}, feels like: ${forecast.list[0].main.feels_like}, clouds: ${forecast.list[0].weather[0].description}")
                val intent: Intent = Intent(this@MainActivity, ForecastListActivity::class.java)
                    .apply { putExtra("forecast", forecast) }
                startActivity(intent)
            }
        }
    }
}