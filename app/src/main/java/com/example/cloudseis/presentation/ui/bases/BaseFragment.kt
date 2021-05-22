package com.example.cloudseis.presentation.ui.bases

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.cloudseis.data.Preferences
import com.example.cloudseis.data.repository.BaseRepository
import com.example.cloudseis.network.RetrofitClient
import com.example.cloudseis.presentation.ui.startNewActivity


abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var preferences: Preferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RetrofitClient()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferences = Preferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        Log.i("BASE FRAGMENT", viewModel.toString())
        //lifecycleScope.launch { userPreferences.authToken.first() }

        return binding.root
    }

//    fun logout() = lifecycleScope.launch {
//        val authToken = userPreferences.authToken.first()
//        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
//        viewModel.logout(api)
//        userPreferences.clear()
//        requireActivity().startNewActivity(AuthActivity::class.java)
//    }

    fun <A : Activity> startNewActivity(activity: Class<A>) {
        requireActivity().startNewActivity(activity)
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R

}
