package com.gerhardrvv.wonder_weather.di

import com.gerhardrvv.wonder_weather.data.IDataService
import com.gerhardrvv.wonder_weather.data.api.IWeatherApiService
import com.gerhardrvv.wonder_weather.data.network.NetworkDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Network
    @Provides
    fun providesNetworkDataService(
        weatherNetworkService: IWeatherApiService
    ): IDataService {
        return NetworkDataService(weatherNetworkService)
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Network
