package com.gerhardrvv.wonder_weather.data

import com.gerhardrvv.wonder_weather.utils.WeatherResponse
import com.gerhardrvv.wonder_weather.utils.WeatherSearchResponse

interface IDataService {

    suspend fun searchLocationId(latLong: MutableMap<String, String>): WeatherSearchResponse

    suspend fun getWeatherFromLocationId(locationId: String): WeatherResponse
}
