package com.core.data.reqres.home.statistik

import com.google.gson.annotations.SerializedName

data class KategoriItem(

	@SerializedName("nama")
	val nama: String,

	@SerializedName("jumlah_akses_user")
	val jumlahAksesUser: String,

	@SerializedName("jumlah_materi")
	val jumlahMateri: String? = null,

	@SerializedName("id")
	val id: String
)