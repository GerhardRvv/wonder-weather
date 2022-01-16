package com.gerhardrvv.wonder_weather.data.models

import com.squareup.moshi.Json

data class WeatherModel(
    @field:Json(name = "consolidated_weather") var consolidated_weather: List<ConsolidateWeatherModel>,
    @field:Json(name = "location_type") var location_type: String? = null,
    @field:Json(name = "title") var title: String? = null
)
