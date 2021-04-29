package com.example.cloudseis.presentation.ui.map

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.cloudseis.R
import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.responses.RegistrarByIdResponse
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
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import jxl.Sheet
import jxl.Workbook
import kotlinx.coroutines.launch
import java.nio.channels.AsynchronousServerSocketChannel.open


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

    private fun loadNetworks(googleMap: GoogleMap) {
        userPreferences.authToken.asLiveData().observe( this, Observer {
            Log.i("map fragment", it.orEmpty())
            viewModel.getPrivateAndPublicNetworks("Bearer " + it.orEmpty())
            viewModel.networks.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Success -> {
                        lifecycleScope.launch {
                            //Log.i("pr and pub netw success", it.value.publicNetworks.toString())
                            for (network in it.value.publicNetworks){//цикл по общим сетям
                                Log.i("received network id", network.id)
                                viewModel.getRegistrarsByNetworkI(network.id.toLong())
                                viewModel.registrars.observe(viewLifecycleOwner, Observer {
                                    when (it) {
                                        is Resource.Success -> {
                                            lifecycleScope.launch {
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
                                                    var iconId: Int = 0
                                                    if (a.haveConnection == "true"){
                                                        text =  a.name + " in network " + a.networkId + " is working"
                                                        iconId = R.drawable.work_station
                                                    } else {
                                                        text = a.name + " in network " + a.networkId + " is not working"
                                                        iconId = R.drawable.not_work_station
                                                    }
                                                    googleMap.addMarker(
                                                        MarkerOptions()
                                                            .position(marker).title(text)
                                                            .icon(BitmapDescriptorFactory.fromResource(iconId))
                                                    )
                                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 5.0f))
                                                    Log.i("ANSWER", "move camera")
                                                }
                                            }
                                        }
                                        is Resource.Failure -> {
                                            lifecycleScope.launch {
                                                //Log.i("pr and pub netw", "jopa 2")
                                            }
                                        }
                                    }
                                })
                            }
                            for (network in it.value.privateNetworks){//цикл по приватным сетям
                                Log.i("received network id", network.id)
                                viewModel.getRegistrarsByNetworkI(network.id.toLong())
                                viewModel.registrars.observe(viewLifecycleOwner, Observer {
                                    when (it) {
                                        is Resource.Success -> {
                                            lifecycleScope.launch {
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
                                                    var iconId: Int = 0

                                                    if (a.haveConnection == "true"){
                                                        text =  a.name + " in network " + a.networkId + " is working"
                                                        color = BitmapDescriptorFactory.HUE_BLUE
                                                        iconId = R.drawable.work_station
                                                    } else {
                                                        text = a.name + " in network " + a.networkId + " is not working"
                                                        color = BitmapDescriptorFactory.HUE_ORANGE
                                                        iconId = R.drawable.not_work_station
                                                    }
                                                    googleMap.addMarker(
                                                        MarkerOptions()
                                                            .position(marker).title(text)
                                                            .icon(BitmapDescriptorFactory.fromResource(iconId))
                                                    )
                                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 5.0f))
                                                    Log.i("ANSWER", "move camera")
                                                }
                                            }
                                        }
                                        is Resource.Failure -> {
                                            lifecycleScope.launch {
                                                Log.i("pr and pub netw", "jopa 2")
                                            }
                                        }
                                    }
                                })
                            }
                        }
                    }
                    is Resource.Failure -> {//получаем нетворки
                        lifecycleScope.launch {
                            Log.i("pr and pub netw", "jopa")
                        }
                    }
                }
            })
        })

//        googleMap.addMarker(//тестировала иконки, можно удалить
//            MarkerOptions()
//                .position(LatLng(54.00, 48.00)).title("163 str")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.earthquake))
//        )

    }


    fun parseCoordinates(a: RegistrarByIdResponse) : MarkerOptions {
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

        val markerOptions = MarkerOptions()
            .position(marker).title(text)
            .icon(BitmapDescriptorFactory.defaultMarker(color))
        return markerOptions
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
        googleMap.mapType = 4
        loadNetworks(googleMap)

        parseXlsFile(googleMap)
    }

    private fun parseXlsFile(googleMap: GoogleMap) {
        //val assetManager = activity?.assets
        val inputStream = context?.assets?.open("The_Human_Induced_Earthquake_Database.xls")
        val workBook: Workbook = Workbook.getWorkbook(inputStream)
        val sheet = workBook.getSheet(0)
        val rowCount = sheet.rows
        val columnCount = sheet.columns

        for (i in 1..100) {
            var cellLat = sheet.getCell(4, i)
            var cellLon = sheet.getCell(5, i)

            var lat = cellLat.contents.replace(",", ".")
            var lon = cellLon.contents.replace(",", ".")

            Log.i("lat ", lat)
            Log.i("lon", lon)
            googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(lat.toDouble(),
                                    lon.toDouble()))
                    .title("Cause: " + sheet.getCell(2, i).contents.toString())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.earthquake))
            )
        }
    }
}