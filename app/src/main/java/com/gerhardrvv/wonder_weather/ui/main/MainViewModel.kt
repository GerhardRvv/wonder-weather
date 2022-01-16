package com.gerhardrvv.wonder_weather.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gerhardrvv.wonder_weather.data.repository.WeatherRepository
import com.gerhardrvv.wonder_weather.utils.WeatherResponseState
import com.gerhardrvv.wonder_weather.utils.WeatherSearchResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val weatherRepository: WeatherRepository
) : AndroidViewModel(application) {

    private val _weatherSearchLiveData = MutableLiveData<WeatherSearchResponseState>()
    val weatherSearchLiveData: LiveData<WeatherSearchResponseState>
        get() = _weatherSearchLiveData

    private val _weatherLiveData = MutableLiveData<WeatherResponseState>()
    val weatherLiveData: LiveData<WeatherResponseState>
        get() = _weatherLiveData

    fun getLocationId() {
        viewModelScope.launch() {
            weatherRepository.getLocationId().collect {
                _weatherSearchLiveData.value = it
            }
            weatherRepository.getWeather().collect {
                _weatherLiveData.value = it
            }
        }
    }
}
