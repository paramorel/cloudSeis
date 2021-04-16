package com.example.cloudseis.presentation.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cloudseis.R
import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.databinding.FragmentMapBinding
import com.example.cloudseis.network.RegistrarsApi
import com.example.cloudseis.network.Resource
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.bases.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class MapFragment : OnMapReadyCallback, BaseFragment<RegistrarsViewModel, FragmentMapBinding, RegistrarsRepository>(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        var mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        userPreferences = UserPreferences(requireContext())
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        return view
    }

    private fun loadRegistrars(googleMap: GoogleMap) {
        viewModel.getRegistrarsByNetworkI(1)
        viewModel.registrars.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        val i = Log.i("ANSWER:", viewModel.registrars.value.toString())

                        for (a in it.value!!){
                            var first = a.gpsN
                            first = first.replace("E", "");
                            var aa = first.toDouble()/100
                            Log.i("FROM AAA", aa.toString())

                            var second = a.gpsW
                            second = second.replace("S0", "");
                            var bb = second.toDouble()/100
                            val marker = LatLng(aa, bb)
                            var text: String = ""
                            var color: Float = 0F

                            if (a.haveConnection == "true"){
                                text =  a.name + " in network " + a.networkId + " is working"
                                color = BitmapDescriptorFactory.HUE_BLUE
                            } else {
                                text = a.name + " in network " + a.networkId + " is not working"
                                color = BitmapDescriptorFactory.HUE_ORANGE
                            }
                            googleMap.addMarker(
                                MarkerOptions()
                                    .position(marker).title(text)
                                    .icon(BitmapDescriptorFactory.defaultMarker(color))
                            )
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 5.0f))
                            Log.i("ANSWER", "move camera")
                        }
                    }
                }
                is Resource.Failure -> {
                    Log.i("ANSWER:", "FAILURE");
                    val toast = Toast.makeText(context, "Ошибка при загрузке даннызх",
                        Toast.LENGTH_LONG)
                    toast.show()
                }
            }
        })
    }


    override fun getViewModel() = RegistrarsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMapBinding = FragmentMapBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        RegistrarsRepository(remoteDataSource.buildApi(RegistrarsApi::class.java), userPreferences)

    override fun onMapReady(googleMap: GoogleMap) {
        Log.i("Map Fragment", "on map ready")
        loadRegistrars(googleMap)
    }
}