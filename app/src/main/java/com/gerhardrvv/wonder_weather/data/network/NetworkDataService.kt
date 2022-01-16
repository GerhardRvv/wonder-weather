package com.gerhardrvv.wonder_weather.data.network

import com.gerhardrvv.wonder_weather.data.IDataService
import com.gerhardrvv.wonder_weather.data.api.IWeatherApiService
import com.gerhardrvv.wonder_weather.utils.WeatherResponse
import com.gerhardrvv.wonder_weather.utils.WeatherSearchResponse
import javax.inject.Inject

class NetworkDataService @Inject constructor(
    private val weatherService: IWeatherApiService
) : IDataService {

    override suspend fun searchLocationId(latLong: MutableMap<String, String>): WeatherSearchResponse =
        weatherService.getLocationId(latLong)

    override suspend fun getWeatherFromLocationId(locationId: String): WeatherResponse =
        weatherService.getWeatherFromLocationId(locationId)
}
