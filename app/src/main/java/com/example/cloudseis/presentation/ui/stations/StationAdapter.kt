package com.example.cloudseis.presentation.ui.stations

import android.graphics.drawable.Drawable
import android.util.Log
import com.example.cloudseis.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudseis.data.Station
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import kotlinx.android.synthetic.main.station_item.view.*
import java.util.ArrayList


class StationAdapter(
    var stations: ArrayList<RegistrarByIdResponse>,
): RecyclerView.Adapter<StationAdapter.StationsViewHolder>(){

    inner class StationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_item, parent, false)
        return StationsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        holder.itemView.apply {
            station_title.text = "Станция #" + stations[position].id.toString()
            gpsN.text = stations[position].gpsN
            gpsW.text = stations[position].gpsW
            network_name.text = "Сеть #" + stations[position].networkId
            var state = false
            state = stations[position].haveConnection == "true"
            if (state){
                station_image.setImageResource(R.drawable.work_station)
            }else{
                station_image.setImageResource(R.drawable.not_work_station)

            }
        }
    }
}