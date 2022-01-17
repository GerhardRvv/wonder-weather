package com.gerhardrvv.wonder_weather.data

import com.gerhardrvv.wonder_weather.CoroutineRule
import com.gerhardrvv.wonder_weather.data.models.ConsolidateWeatherModel
import com.gerhardrvv.wonder_weather.data.models.WeatherModel
import com.gerhardrvv.wonder_weather.data.models.WeatherSearchModel
import com.gerhardrvv.wonder_weather.data.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {


    private val weatherSearchModel = WeatherSearchModel(
        distance = 342342,
        title= "title",
        location_type = "localType",
        woeid = 3242,
        latt_long = "234234"
    )

    private val  consolidateWeatherModel = listOf(
        ConsolidateWeatherModel(
            weather_state_name = "weatherName",
            weather_state_abbr = "abr",
            min_temp = 2.32F,
            max_temp = 30.34F,
            the_temp = 10.23F
        )
    )

    private val weatherModel = WeatherModel(
        consolidated_weather = consolidateWeatherModel,
        location_type = "locationType",
        title = "title"
    )

    private lateinit var locationDataSource: MockDataService

    private lateinit var weatherRepository: WeatherRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    private fun createMockRepository(
        location: Response<List<WeatherSearchModel>>,
        weather:  Response<WeatherModel>
    ) {
        locationDataSource = MockDataService(weather, location)
        weatherRepository = WeatherRepository(locationDataSource)
    }

    @Test
    fun `get location given lat long`() = coroutineRule.runBlockingTest {
        val location = Response.success(listOf(weatherSearchModel))
        val weather =  Response.success(weatherModel)
        createMockRepository(location, weather)
        // when
        val locationId = weatherRepository.getLocationId("23432")
        // then
        MatcherAssert.assertThat(locationId, IsEqual(3242))
    }

}