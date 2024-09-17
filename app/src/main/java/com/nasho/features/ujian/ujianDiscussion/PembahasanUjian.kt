package com.nasho.features.ujian.ujianDiscussion

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.core.data.reqres.ujian.ujianDiscussion.DataItem
import com.nasho.R
import com.nasho.data.adapter.PembahasanUjianAdapter
import com.nasho.databinding.ActivityPembahasanUjianBinding
import com.nasho.features.ujian.UjianViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PembahasanUjian : AppCompatActivity() {
    private lateinit var binding: ActivityPembahasanUjianBinding
    private val viewModel: UjianViewModel by viewModels()
    private val adapterListPembahasan: PembahasanUjianAdapter by lazy { PembahasanUjianAdapter() }
    private lateinit var dataJawaban: MutableList<DataItem>
    private lateinit var idMengambilUjian: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPembahasanUjianBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        idMengambilUjian = intent.getStringExtra("idMengambilUjian").toString()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerViewPembahasanUjian.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPembahasanUjian.adapter = adapterListPembahasan
        binding.btnKembaliUjian.setOnClickListener {
            finish()
        }
        binding.imageViewBackUjian.setOnClickListener{
            finish()
        }


        // Use the UUID as String in API call
        viewModel.getUjianDiscussion(idMengambilUjian).observe(this@PembahasanUjian) {
            when (it) {
                is Result.Success -> {
                    binding.apply {
                        Log.d("Data Content: ", "${it.data}")
                        if (it.data.data.isEmpty()) {
                        } else {
                            adapterListPembahasan.submitList(it.data.data)
                            dataJawaban = it.data.data.toMutableList()
                        }
                    }
                }

                is Result.Error -> {
                    Toast.makeText(this@PembahasanUjian, it.errorMessage, Toast.LENGTH_SHORT).show()
                    Log.d("Tes", it.errorMessage)
                }

                else -> {
                    Log.d("Tes", "Empty JSON")
                }
            }
        }
    }
}