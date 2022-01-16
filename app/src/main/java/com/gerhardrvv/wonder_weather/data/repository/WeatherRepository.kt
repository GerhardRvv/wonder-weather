package com.gerhardrvv.wonder_weather.data.repository

import android.util.Log
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

    fun getLocationId() = flow {
        emit(WeatherSearchState.Loading<List<WeatherSearchModel>>())
        try {
            val apiSearchResponse = fetchLocationIdFromApi()
            if (apiSearchResponse.isSuccessful) {
                emit(WeatherSearchState.Success<List<WeatherSearchModel>>(apiSearchResponse))
            } else {
                emit(WeatherSearchState.Error<List<WeatherSearchModel>>("No Results for lat long"))
            }
        } catch (e: Exception) {
            Log.e("GERHARD", "$e")
            emit(WeatherSearchState.Error<List<WeatherSearchModel>>("Network error!"))
        }
    }.flowOn(Dispatchers.IO)

    fun getWeather() = flow {
        emit(WeatherState.Loading<List<WeatherModel>>())
        try {
            val apiWeatherResponse = fetchWeatherFromApi()
            if (apiWeatherResponse.isSuccessful) {
                emit(WeatherState.Success<List<WeatherModel>>(apiWeatherResponse))
            } else {
                emit(WeatherState.Error<List<WeatherModel>>("No Weather information available"))
            }
        } catch (e: Exception) {
            Log.e("GERHARD", "$e")
            emit(WeatherState.Error<List<WeatherModel>>("Network Error "))
        }
    }

    private suspend fun fetchLocationIdFromApi(): WeatherSearchResponse =
        networkDataService.searchLocationId(mutableMapOf("lattlong" to "43.648560,-79.385368"))

    private suspend fun fetchWeatherFromApi(): WeatherResponse =
        networkDataService.getWeatherFromLocationId("4118")
}
