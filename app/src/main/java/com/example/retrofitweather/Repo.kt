package com.example.retrofitweather

class Repo(
    private val retrofitWeather: RetrofitWeather
)  {
    suspend fun getWeather(city : String) = retrofitWeather.getAllWeather(city)
}