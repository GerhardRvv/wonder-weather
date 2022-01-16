package com.gerhardrvv.wonder_weather.data.api

import com.gerhardrvv.wonder_weather.utils.WeatherResponse
import com.gerhardrvv.wonder_weather.utils.WeatherSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IWeatherApiService {

    @GET("api/location/search/?")
    suspend fun getLocationId(
        @QueryMap currentLatLong: MutableMap<String, String>
    ): WeatherSearchResponse

    @GET("api/location/{location}")
    suspend fun getWeatherFromLocationId(
        @Path("location") locationId: String
    ): WeatherResponse

    companion object {
        const val WEATHER_BASE_API_URL = "https://www.metaweather.com/"
    }
}
