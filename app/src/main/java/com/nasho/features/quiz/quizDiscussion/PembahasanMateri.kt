package com.nasho.features.quiz.quizDiscussion

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.core.data.network.Result
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.reqres.quiz.quizDiscussion.DataJawaban
import com.nasho.data.adapter.PembahasanAdapter
import com.nasho.databinding.ActivityPembahasanMateriBinding
import com.nasho.features.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PembahasanMateri : AppCompatActivity() {
    private lateinit var binding: ActivityPembahasanMateriBinding
    private val viewModel: QuizViewModel by viewModels()
    private val adapterListPembahasan: PembahasanAdapter by lazy { PembahasanAdapter() }
    private lateinit var dataJawaban: MutableList<DataJawaban>

    // Hardcoded UUID as String
    private val id = "fb0294a4-c71a-44dc-8ef2-fa7a9e586990"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPembahasanMateriBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvPembahasan.layoutManager = LinearLayoutManager(this)
        binding.rvPembahasan.adapter = adapterListPembahasan

        // Use the UUID as String in API call
        viewModel.getQuizDiscussion(id).observe(this@PembahasanMateri) {
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
                    Toast.makeText(this@PembahasanMateri, it.errorMessage, Toast.LENGTH_SHORT).show()
                    Log.d("Tes", it.errorMessage)
                }

                else -> {
                    Log.d("Tes", "Empty JSON")
                }
            }
        }
    }
}
