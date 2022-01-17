package com.gerhardrvv.wonder_weather.data.repository

import com.gerhardrvv.wonder_weather.data.IDataService
import com.gerhardrvv.wonder_weather.data.models.WeatherModel
import com.gerhardrvv.wonder_weather.data.models.WeatherSearchModel
import com.gerhardrvv.wonder_weather.di.Network
import com.gerhardrvv.wonder_weather.utils.WeatherResponse
import com.gerhardrvv.wonder_weather.utils.WeatherSearchResponse
import com.gerhardrvv.wonder_weather.utils.WeatherSearchState
import com.gerhardrvv.wonder_weather.utils.WeatherState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    @Network private val networkDataService: IDataService
) {

    fun getLocationId(latLong: String?) = flow {
        emit(WeatherSearchState.Loading<List<WeatherSearchModel>>())
        try {
            val apiSearchResponse = fetchLocationIdFromApi(latLong)
            if (apiSearchResponse.isSuccessful) {
                emit(WeatherSearchState.Success<List<WeatherSearchModel>>(apiSearchResponse))
            } else {
                emit(WeatherSearchState.Error<List<WeatherSearchModel>>("No Results for lat long"))
            }
        } catch (e: Exception) {
            emit(WeatherSearchState.Error<List<WeatherSearchModel>>("Network error!"))
        }
    }.flowOn(Dispatchers.IO)

    fun getWeather(locationId: String?) = flow {
        emit(WeatherState.Loading<List<WeatherModel>>())
        try {
            val apiWeatherResponse = fetchWeatherFromApi(locationId)
            if (apiWeatherResponse.isSuccessful) {
                emit(WeatherState.Success<List<WeatherModel>>(apiWeatherResponse))
            } else {
                emit(WeatherState.Error<List<WeatherModel>>("No Weather information available"))
            }
        } catch (e: Exception) {
            emit(WeatherState.Error<List<WeatherModel>>("Network Error "))
        }
    }

    private suspend fun fetchLocationIdFromApi(latLong: String?): WeatherSearchResponse =
        networkDataService.searchLocationId(mutableMapOf("lattlong" to latLong))

    private suspend fun fetchWeatherFromApi(locationId: String?): WeatherResponse =
        networkDataService.getWeatherFromLocationId(locationId)
}
