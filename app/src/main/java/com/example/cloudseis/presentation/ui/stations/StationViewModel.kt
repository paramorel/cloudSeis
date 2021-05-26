package com.example.cloudseis.presentation.ui.stations

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.responses.PrivateAndPublicNetworksResponse
import com.example.cloudseis.data.responses.RegistrarByIdResponse
import com.example.cloudseis.network.Answer
import com.example.cloudseis.presentation.ui.bases.BaseViewModel
import kotlinx.coroutines.launch
import com.example.cloudseis.data.Preferences
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import java.util.ArrayList

class StationViewModel(
    private val repository: RegistrarsRepository) : BaseViewModel(repository) {
    private var stations: ArrayList<RegistrarByIdResponse> = arrayListOf<RegistrarByIdResponse>()


    fun onViewCreated(preferences: Preferences, view: StationsFragment) {
        Log.e("stations fragment 11", "saada")
        preferences.authToken.asLiveData().observe(view.viewLifecycleOwner, Observer {
            Log.e("stations fragment", it.orEmpty())
            getPrivateAndPublicNetworks("Bearer " + it.orEmpty())
            Log.i("get private and public", it.orEmpty())

            networks.observe(view.viewLifecycleOwner, Observer {
                when (it) {
                    is Answer.Success -> {
                        view.lifecycleScope.launch {
                            Log.i("pr and pub netw success", it.value.publicNetworks.toString())
                            for (network in it.value.privateNetworks) {//цикл по приватным сетям
                                Log.i("received network id", network.id)
                                getRegistrarsByNetworkI(network.id.toLong())
                                registrars.observe(view.viewLifecycleOwner, Observer {
                                    when (it) {
                                        is Answer.Success -> {
                                            view.lifecycleScope.launch {
                                                for (a in it.value!!) {
                                                    Log.i("in cycle", a.id.toString())
                                                    stations.add(a)
                                                    Log.i("stattttt", stations.toString())
                                                }

                                            }
                                            Log.i("stations", stations.toString())
                                            view.setAdapter(stations)
                                        }
                                        is Answer.Failure -> {
                                            view.lifecycleScope.launch {
                                                Log.i("pr and pub netw", " 2")
                                            }
                                        }
                                    }
                                })
                            }
                        }
                    }
                    is Answer.Failure -> {//получаем нетворки
                        view.lifecycleScope.launch {
                            Log.i("pr and pub netw", "a")
                        }
                    }
                }
            })
        })


    }

    private val _registrars: MutableLiveData<Answer<List<RegistrarByIdResponse>>> =
        MutableLiveData()
    val registrars: MutableLiveData<Answer<List<RegistrarByIdResponse>>>
        get() = _registrars

    private val _networks: MutableLiveData<Answer<PrivateAndPublicNetworksResponse>> =
        MutableLiveData()
    val networks: MutableLiveData<Answer<PrivateAndPublicNetworksResponse>>
        get() = _networks

    @SuppressLint("LongLogTag")
    fun getPrivateAndPublicNetworks(token: String) = viewModelScope.launch {
        Log.i("get private and public method before", "hj")
        _networks.value = Answer.Loading
        _networks.value = repository.getPublicAndPrivateNetworks(token)
        Log.i("get private and public method after", "hj")

    }

    private fun getRegistrarsByNetworkI(networkId: Long) = viewModelScope.launch {
        //Log.i("FROM REG VIEWMODEL", "gerReg")
        _registrars.value = Answer.Loading
        _registrars.value = repository.registrarsByNetworkId(networkId)
    }
}
