package com.example.cloudseis.presentation.ui


import com.example.cloudseis.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.presentation.ui.login.AuthActivity
import com.example.cloudseis.presentation.ui.map.MapActivity
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val userPreferences = UserPreferences(this)

//        userPreferences.authToken.asLiveData().observe(this, Observer {
//            val activity = if (it == null) AuthActivity::class.java else MapActivity::class.java
//            startNewActivity(activity)
//        })

        startNewActivity(NavigationActivity::class.java)
    }
}

