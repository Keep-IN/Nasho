package com.nasho.features.home_materi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.nasho.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.root.applySystemWindowInsets()

        updateProgress()

        binding.apply {
            val judul = getLastLearnedMaterial()

            if (judul.isNullOrEmpty().not()) {
                // Misalnya Anda ingin menampilkan judul materi terakhir di suatu TextView
                textView18.text = "$judul"

                // Tambahkan listener untuk melanjutkan materi terakhir
                if (judul == "Nahwu") {
                    cvLast.setOnClickListener{
                        startActivity(Intent(this@Home, MateriContainer::class.java))
                    }
                } else if (judul == "Sharaf") {
                    cvLast.setOnClickListener{
                        startActivity(Intent(this@Home, SharafContainer::class.java))
                    }
                } else {
                    textView18.text = ""
                }
            }
            cvNahwu.setOnClickListener{
                startActivity(Intent(this@Home, MateriContainer::class.java))
            }
            cvSharaf.setOnClickListener{
                startActivity(Intent(this@Home, SharafContainer::class.java))
            }
        }
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun getLastLearnedMaterial(): String? {
        val sharedPreferences = getSharedPreferences("judul", MODE_PRIVATE)
        return sharedPreferences.getString("judul", "")
    }

    private fun updateProgress(){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getHomeStatistik().observe(this@Home){
                when(it){
                    is Result.Success -> {
                        binding.apply {
                            tvDescN.text = "${"Berhasil Menyelesaikan " + 
                                    it.data.data?.get(0)?.kategori?.get(0)?.jumlahAksesUser + " dari " + 
                                    it.data.data?.get(0)?.kategori?.get(0)?.jumlahMateri}"

                            tvDescS.text = "${"Berhasil Menyelesaikan " +
                                    it.data.data?.get(0)?.kategori?.get(1)?.jumlahAksesUser + " dari " +
                                    it.data.data?.get(0)?.kategori?.get(1)?.jumlahMateri}"
                        }
                    }
                    is Result.Error -> {

                    }
                    else -> {

                    }
                }
            }
        }
    }
}