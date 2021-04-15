package com.example.cloudseis.presentation.ui.map

import com.example.cloudseis.data.repository.RegistrarsRepository
import com.example.cloudseis.presentation.ui.bases.BaseViewModel

class RegistrarsViewModel(
    private val repository: RegistrarsRepository,
) : BaseViewModel(repository) {
}