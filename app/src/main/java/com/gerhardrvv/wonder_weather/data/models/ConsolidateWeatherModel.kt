package com.gerhardrvv.wonder_weather.data.models

import com.squareup.moshi.Json

data class ConsolidateWeatherModel(
    @field:Json(name = "weather_state_name") var weather_state_name: String,
    @field:Json(name = "weather_state_abbr") var weather_state_abbr: String? = null,
    @field:Json(name = "min_temp") var min_temp: Float? = null,
    @field:Json(name = "max_temp") var max_temp: Float? = null,
    @field:Json(name = "the_temp") var the_temp: Float? = null,
)
