package com.apixu.weather.ui.base

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Deepthi on 23/02/2020.
 */
abstract class BaseFragment<VM : BaseViewModel?> : Fragment() {
//    protected var viewModel: VM? = null
    @NonNull
//    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = createViewModel()
    }
}