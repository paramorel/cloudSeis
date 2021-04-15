package com.example.cloudseis.presentation.ui.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cloudseis.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity: AppCompatActivity(), OnMapReadyCallback {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_with_reg)

        (this.supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment?)?.let {
            it.getMapAsync(this)
        }

//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
//        mapFragment.getMapAsync(this)

        //navController = Navigation.findNavController(this, R.id.fragmentMap)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }
}
