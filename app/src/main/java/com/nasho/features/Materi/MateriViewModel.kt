package com.nasho.features.Materi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.core.data.network.Result
import com.core.data.repositories.MateriRepository
import com.core.data.reqres.materi.spesificMateri.SpesificMateriResponse
import com.core.data.reqres.spesifikmateri.SpesifikMateriResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MateriViewModel @Inject constructor(
    private val materiRepository: MateriRepository
) : ViewModel() {

    fun getSpecificMateri(idMateri: String): LiveData<Result<SpesificMateriResponse>> {
        return materiRepository.getSpesificMateri(idMateri)
    }
}
