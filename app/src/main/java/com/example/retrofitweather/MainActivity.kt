package com.example.retrofitweather

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var repo: Repo
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private lateinit var enterCity : EditText
    private lateinit var enterButton: Button
    private lateinit var showImageView: ImageView
    private lateinit var showCity : TextView
    private lateinit var showTemp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        enterCity = findViewById(R.id.edt1)
        enterButton = findViewById(R.id.btn1)
        showImageView = findViewById(R.id.img1)
        showCity = findViewById(R.id.txt1)
        showTemp = findViewById(R.id.txt2)

        repo = Repo(RetrofitWeatherBuilder.getInstance())
        weatherViewModelFactory = WeatherViewModelFactory(repo)
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)

        enterButton.setOnClickListener{
            val city = enterCity.text.toString()
            weatherViewModel.getWeather(city)
            enterCity.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }

        weatherViewModel.weatherLiveData.observe(this){
           val currentWeather = it.current.temp_c
            val weatherType = it.current.condition.text

            showTemp.text = "$weatherType, $currentWeather C"
            val cityName = it.location.name
            val region = it.location.region
            showCity.text = "$cityName, $region"

            val imgLink = "https:${it.current.condition.icon}"
            Glide.with(this).load(imgLink).into(showImageView)
        }
    }
}