package com.saintmarina.weather_lookup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "4bbf38461da80cf4de66adcf6f33a959"
const val API_URL: String = "https://api.openweathermap.org"

class ForecastApi {

    @Parcelize
    data class RootForecast (
        val list: List<Forecast>,
        val city: City,
    ) : Parcelable

    @Parcelize
    data class Forecast(
        val main: Metrics,
        val weather: List<Weather>,
    ): Parcelable

    @Parcelize
    data class Metrics(
        val temp: Float,
        val feels_like: Float,
    ): Parcelable

    @Parcelize
    data class Weather(
        val main: String,
        val description: String,
    ): Parcelable

    @Parcelize
    data class City(
        val name: String,
    ): Parcelable

    private val api = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run { create(OpenWeatherMap::class.java) }

    interface OpenWeatherMap {
        @GET("/data/2.5/forecast")
        suspend fun forecast(
            @Query("q") city: String,
            @Query("appid") apiKey: String,
        ): Response<RootForecast>
    }

    suspend fun getForecast(city: String): RootForecast {
        val response = api.forecast(city, API_KEY)
        if (response.isSuccessful) {
            return api.forecast(city, API_KEY).body()!!
        } else {
            val error = kotlin.runCatching { response.errorBody()?.string() }
            throw RuntimeException("Error: ${response.code()}. ${error}.")
        }
    }
}