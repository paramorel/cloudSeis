package com.example.cloudseis.presentation.ui.stations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudseis.R
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import com.example.cloudseis.databinding.FragmentStationsBinding
import com.example.cloudseis.network.StationsApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import kotlinx.android.synthetic.main.fragment_stations.*


class StationsFragment : BaseFragment<StationViewModel, FragmentStationsBinding, RegistrarsRepository>(){
    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("onViewCreated before", "jkk")
        viewModel.onViewCreated(preferences, this)
        recyclerView = view.findViewById(R.id.main_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        Log.e("onViewCreated after", "it.orEmpty()")
        super.onViewCreated(view, savedInstanceState)

    }

    fun setAdapter(stations: ArrayList<RegistrarByIdResponse>) {
        val adapter =
            StationAdapter(stations)

        recyclerView.adapter = adapter
        Log.i("set_adapter_station", stations.toString())
    }

    override fun getViewModel() = StationViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStationsBinding = FragmentStationsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        RegistrarsRepository(remoteDataSource.buildApi(StationsApi::class.java), preferences)
}