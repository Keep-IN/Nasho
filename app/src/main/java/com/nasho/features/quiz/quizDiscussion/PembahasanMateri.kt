package com.nasho.features.quiz.quizDiscussion

import android.content.Intent
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
import com.core.data.reqres.quiz.userAccessQuiz.IdMengambilQuiz
import com.nasho.data.adapter.PembahasanAdapter
import com.nasho.databinding.ActivityPembahasanMateriBinding
import com.nasho.features.home_materi.Home
import com.nasho.features.quiz.QuizViewModel
import com.nasho.features.quiz.quizGrade.QuizGrade
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PembahasanMateri : AppCompatActivity() {
    private lateinit var binding: ActivityPembahasanMateriBinding
    private val viewModel: QuizViewModel by viewModels()
    private val adapterListPembahasan: PembahasanAdapter by lazy { PembahasanAdapter() }
    private lateinit var dataJawaban: MutableList<DataJawaban>
    private lateinit var idMengambilQuiz: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPembahasanMateriBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        idMengambilQuiz = intent.getStringExtra("idMengambilQuiz").toString()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvPembahasan.layoutManager = LinearLayoutManager(this)
        binding.rvPembahasan.adapter = adapterListPembahasan
        binding.btnKembaliP.setOnClickListener {
            finish()
        }
        binding.imageView10.setOnClickListener{
            finish()
        }


        // Use the UUID as String in API call
        viewModel.getQuizDiscussion(idMengambilQuiz).observe(this@PembahasanMateri) {
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
