package com.example.cloudseis.presentation.ui.bases

import androidx.lifecycle.ViewModel
import com.example.cloudseis.data.repository.AuthRepository
import com.example.cloudseis.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    //suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }
}
