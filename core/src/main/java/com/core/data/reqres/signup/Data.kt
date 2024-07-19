package com.core.data.reqres.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(

	@field:SerializedName("accessToken")
	val accessToken: String
) : Parcelable