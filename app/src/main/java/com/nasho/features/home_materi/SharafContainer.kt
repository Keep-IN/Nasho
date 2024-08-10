package com.nasho.features.home_materi

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.data.network.Result
import com.core.data.reqres.materi.Materi
import com.core.data.reqres.materi.Ujian
import com.nasho.R
import com.nasho.databinding.ActivitySharafContainerBinding
import com.nasho.features.home_materi.adapter.MateriAdapter
import com.nasho.features.home_materi.adapter.UjianAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SharafContainer : AppCompatActivity() {
    private lateinit var binding: ActivitySharafContainerBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var materiAdapterPhase1: MateriAdapter
    private lateinit var ujianAdapterPhase1: UjianAdapter
    private lateinit var materiAdapterPhase2: MateriAdapter
    private lateinit var ujianAdapterPhase2: UjianAdapter
    private lateinit var dataMateri: MutableList<Materi>
    private lateinit var dataUjian: MutableList<Ujian>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySharafContainerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sharaf_container)
        binding.root.applySystemWindowInsets()

        initialRecyclerMateri()
        observeViewModel()

        binding.imageView10.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initialRecyclerMateri(){
        val layoutManagerMateri1 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMateri.layoutManager = layoutManagerMateri1
        materiAdapterPhase1 = MateriAdapter()
        binding.rvMateri.adapter = materiAdapterPhase1
        materiAdapterPhase1.setOnClickItem { selectedMateri ->
            rvClickListenerMateri(selectedMateri)
        }

        // Setup for Materi Phase 2 RecyclerView
        val layoutManagerMateri2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMateri2.layoutManager = layoutManagerMateri2
        materiAdapterPhase2 = MateriAdapter()
        binding.rvMateri2.adapter = materiAdapterPhase2
        materiAdapterPhase2.setOnClickItem { selectedMateri ->
            rvClickListenerMateri(selectedMateri)
        }

        // Setup for Ujian Phase 1 RecyclerView
        val layoutManagerUjian1 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUjian.layoutManager = layoutManagerUjian1
        ujianAdapterPhase1 = UjianAdapter()
        binding.rvUjian.adapter = ujianAdapterPhase1
        ujianAdapterPhase1.setOnClickItem { selectedUjian ->
            rvClickListenerUjian(selectedUjian)
        }

        // Setup for Ujian Phase 2 RecyclerView
        val layoutManagerUjian2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUjian2.layoutManager = layoutManagerUjian2
        ujianAdapterPhase2 = UjianAdapter()
        binding.rvUjian2.adapter = ujianAdapterPhase2
        ujianAdapterPhase2.setOnClickItem { selectedUjian ->
            rvClickListenerUjian(selectedUjian)
        }
    }

    private fun observeViewModel() {
        viewModel.viewModelScope.launch {
            withContext(Dispatchers.Main) {
                viewModel.getMateri("630fc24e-6efb-43a9-a997-b32d19a04606").observe(this@SharafContainer) {
                    when (it) {
                        is Result.Success -> {
                            // Ensure data is available before submitting to the adapter

                            val materiListPhase1 = it.data.data[0].materi[0].materi.filter { it.phase == 1 }
                            val materiListPhase2 = it.data.data[0].materi[1].materi.filter { it.phase == 2 }
                            val ujianListPhase1 = it.data.data[0].materi[0].ujian.filter { it.phase_ujian == 1 }
                            val ujianListPhase2 = it.data.data[0].materi[1].ujian.filter { it.phase_ujian == 2 }

                            materiAdapterPhase1.submitList(materiListPhase1)
                            materiAdapterPhase2.submitList(materiListPhase2)
                            ujianAdapterPhase1.submitList(ujianListPhase1)
                            ujianAdapterPhase2.submitList(ujianListPhase2)
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