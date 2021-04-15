package com.example.cloudseis.presentation.ui.bases

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.cloudseis.data.UserPreferences
import com.example.cloudseis.data.repository.BaseRepository
import com.example.cloudseis.network.RemoteDataSource
import com.example.cloudseis.network.UserApi
import com.example.cloudseis.presentation.ui.login.AuthActivity
import com.example.cloudseis.presentation.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

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
