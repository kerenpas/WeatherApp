package com.example.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.data.model.WeatherInfo


@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val cityName by viewModel.cityName.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = cityName,
            onValueChange = viewModel::onCityNameChanged,
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = viewModel::fetchWeather,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(32.dp))

        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text(
                text = state.error!!,
                color = MaterialTheme.colorScheme.error
            )
            state.weatherInfo != null -> WeatherInfo(state.weatherInfo!!)
        }
    }
}

@Composable
fun WeatherInfo(weather: WeatherInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${weather.temperature}°C",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weather.condition,
                style = MaterialTheme.typography.bodyLarge
            )

            // Note: In a real app, you'd want to load the weather icon here
            // using a library like Coil or Glide
        }
    }
}