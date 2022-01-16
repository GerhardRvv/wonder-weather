package com.gerhardrvv.wonder_weather.data.models

import com.squareup.moshi.Json

data class WeatherSearchModel(
    @field:Json(name = "distance") var distance: Int,
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "location_type") var location_type: String? = null,
    @field:Json(name = "woeid") var woeid: Int? = null,
    @field:Json(name = "latt_long") var latt_long: String? = null
)
