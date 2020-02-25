package com.apixu.weather.ui.main

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apixu.weather.services.NetworkServicesImpl


/**
 * Created by Deepthi on 22/02/2020.
 */
class MainViewModelFactory(private val service: NetworkServicesImpl) : ViewModelProvider.Factory {
    @NonNull
    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}