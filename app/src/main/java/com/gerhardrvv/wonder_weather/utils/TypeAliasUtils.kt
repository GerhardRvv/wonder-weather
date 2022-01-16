package com.gerhardrvv.wonder_weather.utils

import com.gerhardrvv.wonder_weather.data.models.WeatherModel
import com.gerhardrvv.wonder_weather.data.models.WeatherSearchModel
import retrofit2.Response

typealias WeatherSearchResponse = Response<List<WeatherSearchModel>>

typealias WeatherSearchResponseState = WeatherSearchState<List<WeatherSearchModel>>

typealias WeatherResponse = Response<WeatherModel>

typealias WeatherResponseState = WeatherState<List<WeatherModel>>
