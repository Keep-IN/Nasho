package com.core.data.reqres.login

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
) : Parcelable