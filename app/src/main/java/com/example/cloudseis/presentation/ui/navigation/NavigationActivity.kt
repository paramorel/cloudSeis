package com.example.cloudseis.presentation.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.cloudseis.R
import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.login.AuthActivity
import com.example.cloudseis.presentation.ui.login.LoginFragment
import com.example.cloudseis.presentation.ui.map.MapFragment
import com.example.cloudseis.presentation.ui.registrars.RegistrarsFragment
import com.example.cloudseis.presentation.ui.startNewActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private var itemId: Int = 0
    private var fragments: MutableMap<Any, BaseFragment<*, *, *>> =
        HashMap()
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val fragment = if (it == null) LoginFragment() else RegistrarsFragment()
            openFragment(fragment)
        })


        //openFragment(LoginFragment())

        Log.i("NAVIGATION ACTIVITY: ", "onCreate");
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        /*if (intent.action.toString() == "android.intent.action.VIEW") {
            val uri = intent.data
            val token = uri?.getQueryParameter("token")
            val bundle = Bundle()
            val fragment = NewPasswordFragment()
            fragment.arguments = bundleOf("token" to token)
            openFragment(fragment)
        }*/
    }

    fun backToMapFragment() {
        when (itemId) {
            R.id.navigation_map -> {
                openFragment(MapFragment())
            }
        }
    }

    fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navigation_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private var navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            itemId = item.itemId
            when (item.itemId) {
                R.id.navigation_map -> {
                    openFragment(MapFragment())
                    Log.i("NAVIGATION ACTIVITY:", "open map fragment");
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_auth -> {
                    userPreferences.authToken.asLiveData().observe(this, Observer {
                        Log.i("navigation auth", it.orEmpty())
                        val fragment = if (it == null) LoginFragment() else RegistrarsFragment()
                        openFragment(fragment)
                    })
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

}
