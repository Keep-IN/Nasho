package com.nasho.features.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.data.network.Result
import com.core.data.reqres.editprofile.EditProfileResponse
import com.core.data.repositories.ProfilingRepository
import com.core.data.reqres.editpassword.EditPasswordResponse
import com.core.data.reqres.profiling.GetProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfilingRepository
) : ViewModel() {

    val profileLiveData: LiveData<Result<GetProfileResponse>> = profileRepository.getProfile()

    private val _updateProfileLiveData = MutableLiveData<Result<EditProfileResponse>>()
    val updateProfileLiveData: LiveData<Result<EditProfileResponse>> get() = _updateProfileLiveData

    private val _updatePasswordLiveData = MutableLiveData<Result<EditPasswordResponse>>()
    val updatePasswordLiveData: LiveData<Result<EditPasswordResponse>> get() = _updatePasswordLiveData

    fun getProfile() = profileRepository.getProfile()

    fun updateProfile(username: String) {
        profileRepository.updateProfile(username).observeForever { result ->
            _updateProfileLiveData.postValue(result)
        }
    }

    fun updatePassword(
        oldPassword: String,
        password: String,
        retypedPassword: String
    ) {
        profileRepository.updatePassword(password, retypedPassword, oldPassword).observeForever { result ->
            _updatePasswordLiveData.postValue(result)
        }
    }
}
