package com.example.retrofitweather

import com.example.retrofitweather.weatherdata.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitWeather {

    @GET("v1/current.json?key=1db42bc4dfce4361a7745015241805")
    suspend fun getAllWeather(
        @Query("q") city : String
    ) : Response<WeatherData>

}