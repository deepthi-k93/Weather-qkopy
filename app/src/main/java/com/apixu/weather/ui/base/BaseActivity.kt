package com.apixu.weather.ui.base

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Deepthi on 22/02/2020.
 */
abstract class BaseActivity<VM : BaseViewModel?> : AppCompatActivity() {
    protected var viewModel: VM? = null
    @NonNull
    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }
}