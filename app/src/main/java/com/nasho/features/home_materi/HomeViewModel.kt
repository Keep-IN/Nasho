package com.nasho.features.home_materi

import androidx.lifecycle.ViewModel
import com.core.data.repositories.MateriRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MateriRepository
): ViewModel() {
    fun getMateri(
        kategori: String
    ) = repository.getMateri(kategori)

    fun getSpesificMateri(id: String) = repository.getSpesificMateri(id)

    fun getMateriKategori() = repository.getMateriKategori()

    fun postAccessMateri(id: String) = repository.postAccesMateri(id)

    fun getHomeStatistik() = repository.getHomeStatistik()

}