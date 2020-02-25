package com.apixu.weather.services

import com.apixu.weather.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("current")
    fun getCurrentWeather(@Query("access_key")key:String,@Query("query") location:String): Call<WeatherResponse?>?

    @GET("forecast")
    fun getForeCastWeather(@Query("access_key")key:String,@Query("query") location:String): Call<WeatherResponse?>?

}