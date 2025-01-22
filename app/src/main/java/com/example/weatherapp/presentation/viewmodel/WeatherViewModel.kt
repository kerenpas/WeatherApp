package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.WeatherInfo
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherViewState())
    val state: StateFlow<WeatherViewState> = _state.asStateFlow()

    private val _cityName = MutableStateFlow("")
    val cityName: StateFlow<String> = _cityName.asStateFlow()

    fun onCityNameChanged(newCity: String) {
        _cityName.value = newCity
    }

    fun fetchWeather() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            getWeatherUseCase(_cityName.value)
                .onSuccess { weatherInfo ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        weatherInfo = weatherInfo
                    )
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message ?: "Unknown error occurred"
                    )
                }
        }
    }
}

data class WeatherViewState(
    val isLoading: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val error: String? = null
)