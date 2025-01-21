package com.example.weatherapp.data.model

data class WeatherResponse(
    val main: MainWeather,
    val weather: List<Weather>,
    val name: String
)

data class MainWeather(
    val temp: Double,
    val humidity: Int,
    val pressure: Int
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherInfo(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val iconUrl: String
)
