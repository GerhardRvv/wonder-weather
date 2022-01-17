package com.gerhardrvv.wonder_weather.data

import com.gerhardrvv.wonder_weather.data.models.WeatherModel
import com.gerhardrvv.wonder_weather.data.models.WeatherSearchModel
import com.gerhardrvv.wonder_weather.utils.WeatherResponse
import com.gerhardrvv.wonder_weather.utils.WeatherSearchResponse
import retrofit2.Response

class MockDataService(
    private var weather: WeatherResponse,
    private var location: WeatherSearchResponse
) : IDataService {

    var fakeLocationResponse = mutableListOf<WeatherSearchModel>()

    override suspend fun searchLocationId(latLong: MutableMap<String, String?>): WeatherSearchResponse {
        return location
    }

    override suspend fun getWeatherFromLocationId(locationId: String?): WeatherResponse {
        return weather
    }


}