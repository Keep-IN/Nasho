package com.nasho.features.home_materi

import androidx.lifecycle.ViewModel
import com.core.data.repositories.MateriRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MateriRepository
): ViewModel() {
    fun getMateri() = repository.getMateri()

}