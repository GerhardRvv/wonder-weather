package com.gerhardrvv.wonder_weather.data

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
) {

//    val weatherUpdate: Flow<List<WeatherModel>> = flow {
//
//    }
//        .flowOn(ioDispatcher)
}
