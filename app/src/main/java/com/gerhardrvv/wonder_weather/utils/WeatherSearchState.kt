package com.gerhardrvv.wonder_weather.utils

import com.gerhardrvv.wonder_weather.data.models.WeatherModel
import com.gerhardrvv.wonder_weather.data.models.WeatherSearchModel

/**
 * Handle weather update states
 */
sealed class WeatherSearchState<T> {
    data class Success<T>(val locations: WeatherSearchResponse) : WeatherSearchState<List<WeatherSearchModel>>()
    data class Error<T>(val errorMsg: String) : WeatherSearchState<T>()
    class Loading<T> : WeatherSearchState<T>()
}

sealed class WeatherState<T> {
    data class Success<T>(val weather: WeatherResponse) : WeatherState<List<WeatherModel>>()
    data class Error<T>(val errorMsg: String) : WeatherState<T>()
    class Loading<T> : WeatherState<T>()
}
