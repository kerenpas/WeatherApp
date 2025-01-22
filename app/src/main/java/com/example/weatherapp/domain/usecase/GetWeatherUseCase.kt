package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.model.WeatherInfo
import com.example.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<WeatherInfo> {
        if (cityName.isBlank()) {
            return Result.failure(IllegalArgumentException("City name cannot be empty"))
        }
        return repository.getWeatherForCity(cityName)
    }
}