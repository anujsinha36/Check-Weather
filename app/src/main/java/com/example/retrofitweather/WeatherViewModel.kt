package com.example.retrofitweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitweather.weatherdata.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repo: Repo
) : ViewModel() {
    val weatherLiveData = MutableLiveData<WeatherData>()

    //init {
       // getWeather(city = "Delhi")
    //}

    fun getWeather(city : String){
        viewModelScope.launch(Dispatchers.IO) {
            val getWeatherData = repo.getWeather(city)
            if (getWeatherData.isSuccessful){
                weatherLiveData.postValue(getWeatherData.body())
            }
        }


    }


}