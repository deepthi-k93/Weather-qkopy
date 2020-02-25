package com.apixu.weather.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.apixu.weather.R
import com.apixu.weather.model.Forecast
import com.apixu.weather.model.WeatherResponse
import com.apixu.weather.services.DataManager
import com.apixu.weather.ui.base.BaseActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel>() {
    var adapter :ForeCastAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel?.loadingStatus?.observe(this, LoadingObserver())
        viewModel?.weatherData?.observe(this, WeatherObserver())
        viewModel?.error?.observe(this, ErrorMessage())
        viewModel?.loadData()
        adapter = ForeCastAdapter()
        recyclerView!!.adapter = adapter
        progressBar?.setOnClickListener {
            viewModel?.loadData()
        }
        retryButton?.setOnClickListener {
            viewModel?.loadData()
        }
    }

    override fun createViewModel(): MainViewModel {
        val factory = DataManager().getInstance()?.getNetworkServices()?.let { MainViewModelFactory(it)}
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }


    private inner class WeatherObserver : Observer<WeatherResponse?> {
        override fun onChanged(@Nullable weather: WeatherResponse?) {
            Log.i("response",Gson().toJson(weather))
            if (weather==null) return
            if(weather.current!=null)
                current_temp?.text =weather.current.temperature.toString()+"\u00B0"
            if(weather.location!=null)
                current_loc?.text =weather.location.name.toString()
            if(weather.forecast!=null)
            {
                val fore = weather.forecast
                val entry: Map.Entry<String, Forecast> = fore.entries.iterator().next()
                val foreArr:ArrayList<Forecast> = ArrayList()
                foreArr.add(entry.value)
                Log.i("foreArr",Gson().toJson(foreArr))
                recyclerView.layoutManager =
                    LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL, false)
                adapter!!.setItems(foreArr)
            }

        }
    }
    private inner class LoadingObserver : Observer<Boolean?> {
        override fun onChanged(@Nullable isLoading: Boolean?) {
            if (isLoading == null) return
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                main_detail.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                main_detail.visibility = View.VISIBLE

            }
        }
    }
    private inner class ErrorMessage:Observer<String>
    {
        override fun onChanged(t: String?) {
            main_detail.visibility = View.GONE
            error_screen.visibility = View.VISIBLE
//            Toast.makeText(applicationContext,t,Toast.LENGTH_LONG).show()
        }

    }
}
