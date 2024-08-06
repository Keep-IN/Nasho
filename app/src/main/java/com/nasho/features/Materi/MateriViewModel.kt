package com.nasho.features.Materi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.core.data.network.Result
import com.core.data.repositories.MateriRepositry
import com.core.data.reqres.spesifikmateri.SpesifikMateriResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MateriViewModel @Inject constructor(
    private val materiRepository: MateriRepositry
) : ViewModel() {

    fun getSpecificMateri(idMateri: Int): LiveData<Result<SpesifikMateriResponse>> {
        return materiRepository.spekmateri(idMateri)
    }
}
