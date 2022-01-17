package com.gerhardrvv.wonder_weather.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class Helpers @Inject constructor() {

    fun roundNumber(num: Float?): String {
        val df = DecimalFormat("#")
        df.roundingMode = RoundingMode.CEILING
        return (df.format(num))
    }
}
