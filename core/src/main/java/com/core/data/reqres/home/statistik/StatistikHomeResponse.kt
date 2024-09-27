package com.core.data.reqres.home.statistik

import com.google.gson.annotations.SerializedName

data class StatistikHomeResponse(

	@SerializedName("msg")
	val msg: String,

	@SerializedName("data")
	val data: List<DataItem>
)