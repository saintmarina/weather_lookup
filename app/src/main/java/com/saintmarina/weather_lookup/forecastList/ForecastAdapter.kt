package com.saintmarina.weather_lookup.forecastList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.saintmarina.weather_lookup.ForecastApi.Forecast
import com.saintmarina.weather_lookup.R
import com.saintmarina.weather_lookup.Util

class ForecastAdapter(context: Context, private val resource: Int, private val onClick: (Forecast) -> Unit) :
    ArrayAdapter<Forecast>(context, resource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val forecast = this.getItem(position)!!
        val item = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
        return item.apply {
            setOnClickListener { onClick(forecast) }
            findViewById<TextView>(R.id.weather_text).text = Util.formattedWeather(forecast.weather[0].main)
            findViewById<TextView>(R.id.temperature_text).text = context.getString(R.string.temp_text, Util.formattedTemperature(forecast.main.temp))
        }
    }
}