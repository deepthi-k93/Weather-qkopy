package com.apixu.weather.model

class WeatherResponse{
    val current: Current?=null
    val location: Location?=null
    val request: Request?=null
    val forecast: Map<String, Forecast>?=null
    var error:ErrorTest?=null
    var success: Boolean?=true
}