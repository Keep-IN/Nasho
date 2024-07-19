package com.core.data.reqres.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignupResponse(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("data")
	val data: Data
) : Parcelable