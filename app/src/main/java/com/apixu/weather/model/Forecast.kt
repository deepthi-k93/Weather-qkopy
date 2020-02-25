package com.apixu.weather.model

data class Forecast(
    val astro: Astro,
    val avgtemp: Int,
    val date: String,
    val date_epoch: Int,
    val maxtemp: Int,
    val mintemp: Int,
    val sunhour: Double,
    val totalsnow: Int,
    val uv_index: Int
)