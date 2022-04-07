package com.saintmarina.weather_lookup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.saintmarina.weather_lookup.forecastList.ForecastListActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    private val coroutine = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.input)
        val button = findViewById<Button>(R.id.lookup)
        val api = ForecastApi()

        button.setOnClickListener{
            coroutine.launch {
                try {
                    val rootForecast = api.getForecast(input.text.toString())
                    val intent: Intent = Intent(this@MainActivity, ForecastListActivity::class.java)
                        .apply { putExtra("rootForecast", rootForecast) }
                    startActivity(intent)
                } catch (e: RuntimeException) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}