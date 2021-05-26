package com.example.cloudseis.presentation.ui


import android.content.Intent
import com.example.cloudseis.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.cloudseis.data.Preferences
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity
import com.example.cloudseis.services.NotificationService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = Preferences(this)

//        userPreferences.authToken.asLiveData().observe(this, Observer {
//            if (it != null)
//                startService(Intent(this, NotificationService::class.java))
//        })

        startNewActivity(NavigationActivity::class.java)
    }
}

