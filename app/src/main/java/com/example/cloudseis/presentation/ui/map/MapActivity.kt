package com.example.cloudseis.presentation.ui.map

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cloudseis.R
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity
import com.example.cloudseis.presentation.ui.startNewActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_auth.*

class MapActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Map Activity", "on Create")
        setContentView(R.layout.activity_map_with_reg)

    }
}
