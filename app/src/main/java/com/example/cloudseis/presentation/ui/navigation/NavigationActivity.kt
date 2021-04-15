package com.example.cloudseis.presentation.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cloudseis.R
import com.example.cloudseis.presentation.ui.bases.BaseFragment
import com.example.cloudseis.presentation.ui.map.MapFragment
import com.example.cloudseis.presentation.ui.registrars.RegistrarsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private var itemId: Int = 0
    private var fragments: MutableMap<Any, BaseFragment<*, *, *>> =
        HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        openFragment(MapFragment())
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
//            R.id.navigation_registrars -> {
//                openFragment(LikedRecipesFragment())
//            }
        }
    }

    fun openFragment(fragment: Fragment) {

        when (fragment) {
            is MapFragment -> bottom_navigation.selectedItemId = R.id.navigation_map
            is RegistrarsFragment -> bottom_navigation.selectedItemId = R.id.navigation_registrars

        }
        openFragmentWithoutSetSelectItemId(fragment)
    }

    fun openFragmentWithoutSetSelectItemId(fragment: Fragment) {
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
                    openFragmentWithoutSetSelectItemId(getFragment(MapFragment::class.java))

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_registrars -> {
                    openFragmentWithoutSetSelectItemId(getFragment(RegistrarsFragment::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


    private fun <T: BaseFragment<*, *, *>> getFragment(clazz: Class<T>): T {
        if (!fragments.containsKey(clazz)) {
            fragments[clazz] = clazz.getConstructor().newInstance() as T;
        }
        return fragments[clazz] as T;
    }

}
