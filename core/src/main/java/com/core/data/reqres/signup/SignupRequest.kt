package com.core.data.reqres.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignupRequest(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("retyped-password")
	val retypedPassword: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
) : Parcelable