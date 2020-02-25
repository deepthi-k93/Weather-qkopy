package com.apixu.weather.ui.main

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.apixu.weather.GlobalConstants
import com.apixu.weather.model.WeatherResponse
import com.apixu.weather.services.NetworkServicesImpl
import com.apixu.weather.ui.base.BaseViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val mApiServices: NetworkServicesImpl) : BaseViewModel() {
    var weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()
    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    private val accessKey = GlobalConstants.accessKey

    fun loadData() {
        setIsLoading(true)
//        mApiServices.getApiServices()?.getCurrentWeather(accessKey,"Calicut")?.enqueue(CurrentWeatherCallback())
        mApiServices.getApiServices()?.getForeCastWeather(accessKey,"Calicut")?.enqueue(ForecastWeatherCallback())
    }
    private fun setIsLoading(loading: Boolean) {
        loadingStatus.postValue(loading)
    }
    fun setWeatherDetail(current: WeatherResponse) {
        setIsLoading(false)
        this.weatherData.postValue(current)
    }
    fun setError(err: String) {
        setIsLoading(false)
        this.error.postValue(err)
    }
    /**
     * Callback
     */
    inner class CurrentWeatherCallback : Callback<WeatherResponse?> {
        override fun onResponse(@NonNull call: Call<WeatherResponse?>, @NonNull response: Response<WeatherResponse?>) {
//            var liveData: MutableLiveData<WeatherResponse> = MutableLiveData()
//            var weather: WeatherResponse? = null
            Log.i("weatherCurrent",Gson().toJson(response))
            if(response.isSuccessful && response.code()==200) {
                val responseCurrent: WeatherResponse? = response.body()
                Log.i("weatherCurrent", Gson().toJson(responseCurrent))
                if (responseCurrent != null) {
                    setWeatherDetail(responseCurrent)
                }
            }
            /*else {
                setWeatherDetail(responseCurrent)
            }*/
        }


        override fun onFailure(
            call: Call<WeatherResponse?>,
            t: Throwable
        ) {
//            setMovies(emptyList<Movie>())
        }
    }

    inner class ForecastWeatherCallback : Callback<WeatherResponse?> {
        override fun onResponse(@NonNull call: Call<WeatherResponse?>, @NonNull response: Response<WeatherResponse?>) {
            val res = response
            Log.i("weatherFore",Gson().toJson(res))
            if(response.body()?.success!!) {
                val responseFore: WeatherResponse? = response.body()
                Log.i("weatherFore", Gson().toJson(responseFore))
                if (responseFore != null) {
                    setWeatherDetail(responseFore)
                }
            }

            else
            {
                Log.i("weatherForeErr", Gson().toJson(response.body()?.error))
                setError(response.body()?.error?.info.toString())

            }
        }

        override fun onFailure(
            call: Call<WeatherResponse?>,
            t: Throwable
        ) {
            setError(t.message.toString())
        }
    }

}