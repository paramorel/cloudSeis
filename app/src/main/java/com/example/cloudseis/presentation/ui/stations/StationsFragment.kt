package com.example.cloudseis.presentation.ui.stations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudseis.R
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.databinding.FragmentStationsBinding
import com.example.cloudseis.network.StationsApi
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.map.RegistrarsViewModel

class StationsFragment : BaseFragment<RegistrarsViewModel, FragmentStationsBinding, RegistrarsRepository>(){
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stations, container, false)
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.layoutManager = LinearLayoutManager(view.context);
        recyclerView.adapter = RandomNumListAdapter(1234);

        return view;
    }

    override fun getViewModel() = RegistrarsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStationsBinding = FragmentStationsBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        RegistrarsRepository(remoteDataSource.buildApi(StationsApi::class.java), preferences)
}