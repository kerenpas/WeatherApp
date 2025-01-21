import com.example.weatherapp.BuildConfig

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.model.WeatherInfo
import com.example.weatherapp.data.repository.WeatherRepository

import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherForCity(cityName: String): Result<WeatherInfo> {
        return try {
            val response = api.getWeather(
                cityName = cityName,
                apiKey = BuildConfig.WEATHER_API_KEY
            )

            Result.success(
                WeatherInfo(
                    cityName = response.name,
                    temperature = response.main.temp,
                    condition = response.weather.firstOrNull()?.main ?: "",
                    iconUrl = "https://openweathermap.org/img/w/${response.weather.firstOrNull()?.icon}.png"
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
