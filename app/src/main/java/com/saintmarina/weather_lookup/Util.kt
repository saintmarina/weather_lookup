package com.saintmarina.weather_lookup

class Util {
    companion object {
        fun prettyTemp(tempInKelvin: Float): String {
            return String.format("%.2f", tempInKelvin * 1.8F - 459.67F)
        }

        fun prettyWeather(weather: String): String {
            return when (weather) {
                "Rain" -> "Rainy"
                "Clouds" -> "Cloudy"
                else -> weather
            }
        }
    }
}