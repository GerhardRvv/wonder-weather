package com.gerhardrvv.wonder_weather.ui.main.location

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.gerhardrvv.wonder_weather.R
import com.gerhardrvv.wonder_weather.data.models.ConsolidateWeatherModel
import com.gerhardrvv.wonder_weather.databinding.LocationFragmentBinding
import com.gerhardrvv.wonder_weather.ui.main.MainViewModel
import com.gerhardrvv.wonder_weather.utils.Helpers
import com.gerhardrvv.wonder_weather.utils.WeatherSearchState
import com.gerhardrvv.wonder_weather.utils.WeatherState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment @Inject constructor() : Fragment(R.layout.location_fragment) {

    private var locationFragmentBinding: LocationFragmentBinding? = null

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var helpers: Helpers

    private var lat: String? = null
    private var long: String? = null
    private var locationLatLong: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationFragmentBinding = LocationFragmentBinding.bind(view)
        locationFragmentBinding?.getLocationButton?.setOnClickListener {
            requestLocationPermissions()
            locationLatLong = "$lat,$long"
        }
        requestLocationPermissions()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeWeather() {
        mainViewModel.weatherSearchLiveData.observe(
            viewLifecycleOwner,
            Observer { viewState ->
                when (viewState) {
                    is WeatherSearchState.Success<*> -> {
                        if (viewState.locations.isSuccessful && viewState.locations.body() != null) {
                            val locationId = viewState.locations.body()!![0].woeid.toString()
                            mainViewModel.getWeatherWithId(locationId)
                            Toast.makeText(context, "Success !!", Toast.LENGTH_LONG).show()
                        }
                    }
                    is WeatherSearchState.Loading -> {
                        locationFragmentBinding?.locationFragment?.visibility = GONE
                        locationFragmentBinding?.loader?.visibility = VISIBLE
                    }
                    is WeatherSearchState.Error -> Toast.makeText(context, "Oops  Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        )

        mainViewModel.weatherLiveData.observe(
            viewLifecycleOwner,
            Observer { viewState ->
                when (viewState) {
                    is WeatherState.Success<*> -> {
                        if (viewState.weather.isSuccessful && viewState.weather.body() != null) {
                            val currentWeather = viewState.weather.body()
                            val consolidatedWeather = currentWeather?.consolidated_weather?.get(0)
                            setWeatherIcon(consolidatedWeather)
                            locationFragmentBinding?.locationTitleTextview?.text = currentWeather?.title
                            locationFragmentBinding?.currentForecastTextview?.text = consolidatedWeather?.weather_state_name.toString()
                            locationFragmentBinding?.currentTempTextview?.text = helpers.roundNumber(consolidatedWeather?.the_temp)
                            locationFragmentBinding?.lowestTempTextview?.text = "M: ${helpers.roundNumber(consolidatedWeather?.min_temp)} H: ${helpers.roundNumber(consolidatedWeather?.max_temp)}"
                        }
                        locationFragmentBinding?.loader?.visibility = GONE
                        locationFragmentBinding?.locationFragment?.visibility = VISIBLE
                    }
                    is WeatherState.Loading -> {
                        locationFragmentBinding?.locationFragment?.visibility = GONE
                        locationFragmentBinding?.loader?.visibility = VISIBLE
                    }
                    is WeatherState.Error -> Toast.makeText(context, "Oops  Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        )
        mainViewModel.getLocationId(locationLatLong)
    }

    private fun setWeatherIcon(consolidatedWeather: ConsolidateWeatherModel?) {
        val imageView = locationFragmentBinding?.weatherIcon
        val weatherIcon = "${consolidatedWeather?.weather_state_abbr}.png"
        context?.let {
            if (imageView != null) {
                Glide.with(it).load("https://www.metaweather.com/static/img/weather/png/$weatherIcon").into(imageView)
            }
        }
    }

    private fun requestLocationPermissions() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        when {
            ContextCompat.checkSelfPermission(
                context!!,
                ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location ->
                        lat = location.latitude.toString()
                        long = location.longitude.toString()
                        locationLatLong = "$lat,$long"
                        if (locationLatLong != null) {
                            initializeWeather()
                        }
                    }
            }
            shouldShowRequestPermissionRationale("We need to get you location") -> {
                Toast.makeText(context!!, "We need your location please", Toast.LENGTH_LONG).show()
            }
            else -> {
                requestPermissionLauncher.launch(
                    ACCESS_COARSE_LOCATION
                )
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted: Boolean ->
        if (isGranted) {
            requestLocationPermissions()
        } else {
            Toast.makeText(context!!, "We need your location", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        locationFragmentBinding = null
        super.onDestroyView()
    }
}
