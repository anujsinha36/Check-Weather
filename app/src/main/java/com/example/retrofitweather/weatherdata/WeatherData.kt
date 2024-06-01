package com.example.retrofitweather.weatherdata

import com.example.retrofitweather.weatherdata.Current
import com.example.retrofitweather.weatherdata.Location

data class WeatherData(
    val current: Current,
    val location: Location
)