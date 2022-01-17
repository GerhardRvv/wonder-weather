package com.gerhardrvv.wonder_weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gerhardrvv.wonder_weather.databinding.MainActivityBinding
import com.gerhardrvv.wonder_weather.ui.main.location.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        callLocationFragment(savedInstanceState, mainActivityBinding)
    }

    private fun callLocationFragment(
        savedInstanceState: Bundle?,
        mainActivityBinding: MainActivityBinding
    ) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(mainActivityBinding.fragmentContainerView.id, LocationFragment())
                addToBackStack(null)
                commit()
            }
        }
    }
}
