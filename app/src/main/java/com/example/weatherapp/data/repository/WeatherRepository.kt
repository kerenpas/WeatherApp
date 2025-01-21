package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherInfo


interface WeatherRepository {
    suspend fun getWeatherForCity(cityName: String): Result<WeatherInfo>

}