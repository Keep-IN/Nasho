package com.core.data.reqres.home.statistik

import com.google.gson.annotations.SerializedName

data class DataItem(

	@SerializedName("kategori")
	val kategori: List<KategoriItem>,

	@SerializedName("usernasho")
	val usernasho: String
)