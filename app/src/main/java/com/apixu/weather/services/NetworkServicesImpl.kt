package com.apixu.weather.services

import com.apixu.weather.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServicesImpl() {

    @Volatile private var sInstance: NetworkServicesImpl? = null
    private var mRetrofit: Retrofit? = null
    var mAPIServices: ApiServices? = null

    fun getInstance(): NetworkServicesImpl? {
        if (null == sInstance) {
            synchronized(NetworkServicesImpl::class.java) {
                sInstance = NetworkServicesImpl()
            }
        }
        return sInstance
    }

    init {
        mRetrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.BASE_URL).build()
        mAPIServices = mRetrofit?.create<ApiServices>(ApiServices::class.java)
    }

    fun getApiServices(): ApiServices? {
        return mAPIServices
    }
}