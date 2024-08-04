package com.nasho.features.home_materi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.data.DataRewinder
import com.core.data.network.Result
import com.core.data.reqres.materi.Data
import com.core.data.reqres.materi.DataMateri
import com.core.data.reqres.materi.Materi
import com.core.data.reqres.materi.Ujian
import com.nasho.R
import com.nasho.databinding.ActivityMateriContainerBinding
import com.nasho.features.home_materi.adapter.MateriAdapter
import com.nasho.features.home_materi.adapter.UjianAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MateriContainer : AppCompatActivity() {
    private lateinit var binding: ActivityMateriContainerBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var materiAdapter: MateriAdapter
    private lateinit var ujianAdapter: UjianAdapter
    private lateinit var dataMateri: MutableList<Materi>
    private lateinit var dataUjian: MutableList<Ujian>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMateriContainerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.root.applySystemWindowInsets()

        initialRecyclerMateri()
        observeViewModel()

        binding.imageView10.setOnClickListener {
            finish()
        }
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initialRecyclerMateri(){
        val layoutManagerMateri1 =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMateri.layoutManager = layoutManagerMateri1

        val layoutManagerMateri2 =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMateri2.layoutManager = layoutManagerMateri2

        materiAdapter = MateriAdapter()

        binding.rvMateri.adapter = materiAdapter
        binding.rvMateri2.adapter = materiAdapter

        materiAdapter.setOnClickItem { selectedMateri ->
            rvClickListenerMateri(selectedMateri)
        }

        val layoutManagerUjian =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUjian.layoutManager = layoutManagerUjian

        val layoutManagerUjian2 =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUjian2.layoutManager = layoutManagerUjian2

        ujianAdapter = UjianAdapter()
        binding.rvUjian.adapter = ujianAdapter
        binding.rvUjian2.adapter = ujianAdapter

        ujianAdapter.setOnClickItem { selectedProduct ->
            rvClickListenerUjian(selectedProduct)
        }
    }

    private fun observeViewModel() {
        viewModel.viewModelScope.launch {
            withContext(Dispatchers.Main) {
                viewModel.getMateri("3053b811-0544-4cea-b951-1b5f0b9ab36f").observe(this@MateriContainer) {
                    when (it) {
                        is Result.Success -> {
                            // Ensure data is available before submitting to the adapter

                            materiAdapter.submitListPhase1(it.data.data[0].materi[0].materi)
                            ujianAdapter.submitListPhase1(it.data.data[0].materi[0].ujian)
                            materiAdapter.submitListPhase2(it.data.data[0].materi[0].materi)
                            ujianAdapter.submitListPhase2(it.data.data[0].materi[0].ujian)
                            Log.d("ntah", it.data.data[0].materi[0].materi.toString())
//                            materiAdapter.submitListPhase1(dataMateri)
//                            ujianAdapter.submitListPhase1(dataUjian)
//                            materiAdapter.submitListPhase2(dataMateri)
//                            ujianAdapter.submitListPhase2(dataUjian)
                        }
                        is Result.Error -> {
                            Log.d("error getMateri", it.errorMessage)
                        }
                        else -> {
                            Log.d("Unexpected Error", "error")
                        }
                    }
                }
            }
        }
    }

    private val rvClickListenerMateri: (Materi) -> Unit = { item ->
        //startActivity(Intent(this, Materi::class.java).apply {
        //    putExtra("id", item)
        //})
    }

    private val rvClickListenerUjian: (Ujian) -> Unit = { item ->
        //startActivity(Intent(this, Ujian::class.java).apply {
        //    putExtra("id", item)
        //})
    }
}