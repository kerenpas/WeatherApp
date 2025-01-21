# Weather App

A simple Android weather application built with Kotlin and Jetpack Compose that displays current weather information for a given location using the OpenWeatherMap API.

## Features

- Search weather by city name
- Display current temperature and weather conditions
- Clean Architecture implementation
- Modern Android development practices
- Error handling and loading states

## Architecture

The app follows Clean Architecture principles and is organized into three layers:

- **Data Layer**: Handles data operations and external services
- **Domain Layer**: Contains business logic and use cases
- **Presentation Layer**: Manages UI and user interactions

### Tech Stack

- Kotlin
- Jetpack Compose for UI
- Retrofit for networking
- Dagger Hilt for dependency injection
- Kotlin Coroutines for asynchronous operations
- MVVM architecture pattern

## Setup

1. Clone the repository
2. Get an API key from [OpenWeatherMap](https://openweathermap.org/api)
3. Add your API key to `local.properties`:
   ```
   WEATHER_API_KEY=your_api_key_here
   ```
4. Build and run the project

## Project Structure

```
app/
├── data/
│   ├── api/
│   ├── models/
│   └── repository/
├── domain/
│   ├── models/
│   ├── repository/
│   └── usecase/
└── presentation/
    ├── ui/
    └── viewmodel/
```

## Dependencies

All dependencies are managed in the app-level `build.gradle.kts` file. Key dependencies include:

- AndroidX Core KTX
- Jetpack Compose
- Retrofit
- Dagger Hilt
- Kotlin Coroutines

## Error Handling

The app implements comprehensive error handling:
- Network errors
- Invalid city names
- API errors
- Empty input validation

## Future Improvements

Potential areas for enhancement:
- Add weather forecasts
- Implement location-based weather
- Add unit tests
- Cache weather data
- Add weather notifications
