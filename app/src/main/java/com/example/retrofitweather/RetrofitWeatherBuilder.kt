package com.example.retrofitweather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWeatherBuilder {

    companion object{
        var retrofitWeather : RetrofitWeather ?= null

        fun getInstance() : RetrofitWeather{
            if (retrofitWeather == null){
                retrofitWeather = Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("https://api.weatherapi.com/").
                build().
                create(RetrofitWeather::class.java)
            }
            return retrofitWeather!!
        }
    }
}