package com.apixu.weather.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apixu.weather.R
import com.apixu.weather.model.Forecast
import java.text.SimpleDateFormat


/**
 * Created by deepthi on 23/3/2020.
 */
class ForeCastAdapter() :
    RecyclerView.Adapter<ForeCastAdapter.MyViewHolder>() {

    var forecast:List<Forecast> = ArrayList()
    fun setItems(items: List<Forecast>) {
        forecast = items
        notifyDataSetChanged()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val inFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inFormat.parse(forecast[position].date)
        val outFormat = SimpleDateFormat("EEEE")
        val goal = outFormat.format(date)
        holder.itemDay?.text = goal
        holder.itemDeg?.text = forecast[position].avgtemp.toString()+"\u2103"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }
    class MyViewHolder : RecyclerView.ViewHolder
    {
        var itemDeg: TextView?=null
        var itemDay: TextView?=null

        constructor(itemView: View) :super(itemView) {
            itemDeg=itemView.findViewById(R.id.fore_degree) as TextView
            itemDay=itemView.findViewById(R.id.day_title) as TextView
        }
    }
}
