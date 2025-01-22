package com.example.weatherapp.data.api


import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.netwoek.annotation.Retry
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    @Retry(maxRetries = 3)
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}
