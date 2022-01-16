package com.gerhardrvv.wonder_weather

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gerhardrvv.wonder_weather.databinding.LocationFragmentBinding
import com.gerhardrvv.wonder_weather.ui.main.MainViewModel
import com.gerhardrvv.wonder_weather.utils.WeatherSearchState
import com.gerhardrvv.wonder_weather.utils.WeatherState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var locationFragmentBinding: LocationFragmentBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private val location = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (location == null) {
            locationFragmentBinding = LocationFragmentBinding.inflate(layoutInflater)
            val view = locationFragmentBinding.root
            setContentView(view)
        } else {
            mainViewModel.weatherSearchLiveData.observe(
                this,
                Observer {
                    when (it) {
                        is WeatherSearchState.Success<*> -> Toast.makeText(applicationContext, "Success !!", Toast.LENGTH_LONG).show()
                        is WeatherSearchState.Loading -> Toast.makeText(applicationContext, "Loading", Toast.LENGTH_SHORT).show()
                        is WeatherSearchState.Error -> Toast.makeText(applicationContext, "Oops  Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            )

            mainViewModel.weatherLiveData.observe(
                this,
                Observer {
                    when (it) {
                        is WeatherState.Success<*> -> Toast.makeText(applicationContext, "Success !!", Toast.LENGTH_LONG).show()
                        is WeatherState.Loading -> Toast.makeText(applicationContext, "Loading", Toast.LENGTH_SHORT).show()
                        is WeatherState.Error -> Toast.makeText(applicationContext, "Oops  Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            )
            mainViewModel.getLocationId()
        }
    }
}
