package com.apixu.weather.services

/**
 * Created by Deepthi on 22/02/2020.
 */
class DataManager() {

    fun getNetworkServices(): NetworkServicesImpl? {
        return NetworkServicesImpl().getInstance()
    }

    private var sInstance: DataManager? = null
    @Synchronized
    fun getInstance(): DataManager? {
        if (sInstance == null) {
            sInstance = DataManager()
        }
        return sInstance
    }

    /* companion object {
        private var sInstance: DataManager? = null
        @get:Synchronized
        val instance: DataManager?
            get() {
                if (sInstance == null) {
                    sInstance = DataManager()
                }
                return sInstance
            }
    }*/
}