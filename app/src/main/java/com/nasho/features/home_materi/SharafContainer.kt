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

@AndroidEntryPoint
class SharafContainer : AppCompatActivity() {
    private lateinit var binding: ActivitySharafContainerBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var materiAdapter: MateriAdapter
    private lateinit var ujianAdapter: UjianAdapter
    private lateinit var dataMateri: MutableList<Materi>
    private lateinit var dataUjian: MutableList<Ujian>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySharafContainerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sharaf_container)
        binding.root.applySystemWindowInsets()

        initialRecyclerMateri()
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getMateri("3053b811-0544-4cea-b951-1b5f0b9ab36f")
                .observe(this@SharafContainer){
                    when(it){
                        is Result.Success -> {
//                            materiAdapter.submitListPhase1(dataMateri)
//                            ujianAdapter.submitListPhase1(dataUjian)
//                            materiAdapter.submitListPhase2(dataMateri)
//                            ujianAdapter.submitListPhase2(dataUjian)
                        }
                        is Result.Error -> {
                            //finished()
                            Log.d("error getMateri", it.errorMessage)
                        }
                        else -> {
                            //loading()
                            Log.d("Unexpected Error", "error")
                        }
                    }
                }
        }

//        //materi
//        val rvMateri = findViewById<RecyclerView>(R.id.rvMateri)
//        val layoutManager = LinearLayoutManager(this@SharafContainer, LinearLayoutManager.HORIZONTAL, false)
//        rvMateri.layoutManager = layoutManager
//        val  materiAdapter = MateriAdapter()
//        rvMateri.adapter = materiAdapter
//
//        //Ujian
//        val rvUjian = findViewById<RecyclerView>(R.id.rvUjian)
//        val layoutManager2 = LinearLayoutManager(this@SharafContainer, LinearLayoutManager.HORIZONTAL, false)
//        rvUjian.layoutManager = layoutManager2
//        val  ujianAdapter = UjianAdapter()
//        rvUjian.adapter = ujianAdapter
    }

    private fun initialRecyclerMateri(){
        val layoutManagerMateri =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMateri.layoutManager = layoutManagerMateri

        materiAdapter = MateriAdapter()
        binding.rvMateri.adapter = materiAdapter

        materiAdapter.setOnClickItem { selectedMateri ->
            rvClickListenerMateri(selectedMateri)
        }

        binding.rvUjian.layoutManager = layoutManagerMateri

        ujianAdapter = UjianAdapter()
        binding.rvUjian.adapter = ujianAdapter

        ujianAdapter.setOnClickItem { selectedProduct ->
            rvClickListenerUjian(selectedProduct)
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