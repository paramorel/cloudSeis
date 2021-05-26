package com.example.cloudseis.presentation.ui


import android.content.Intent
import com.example.cloudseis.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cloudseis.presentation.ui.navigation.NavigationActivity
import com.example.cloudseis.services.NotificationService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //title =
        //startService(Intent(this, NotificationService::class.java))
        setContentView(R.layout.activity_main)
        //val userPreferences = UserPreferences(this)

//        userPreferences.authToken.asLiveData().observe(this, Observer {
//            val activity = if (it == null) AuthActivity::class.java else MapActivity::class.java
//            startNewActivity(activity)
//        })

        startNewActivity(NavigationActivity::class.java)
    }
}

