package com.example.cloudseis.presentation.ui.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.data.repository.BaseRepository
import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.data.repository.StationRepository
import com.example.cloudseis.presentation.ui.login.AuthViewModel
import com.example.cloudseis.presentation.ui.map.RegistrarsViewModel
import com.example.cloudseis.presentation.ui.stations.StationViewModel
import com.example.cloudseis.presentation.ui.stations.StationsFragment
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(RegistrarsViewModel::class.java) -> RegistrarsViewModel(repository as RegistrarsRepository) as T
            modelClass.isAssignableFrom(StationViewModel::class.java) -> StationViewModel(repository as RegistrarsRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}
