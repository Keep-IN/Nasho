package com.core.data.reqres.ujian.userAccessUjian

import com.google.gson.annotations.SerializedName

data class UjianAccessRequest(

	@field:SerializedName("phase")
	val phase: Int,

	@field:SerializedName("kategori_materi")
	val kategoriMateri: String
)