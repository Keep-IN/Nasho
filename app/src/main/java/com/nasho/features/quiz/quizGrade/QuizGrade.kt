package com.nasho.features.quiz.quizGrade

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.nasho.R
import com.nasho.databinding.ActivityQuizGradeBinding
import com.nasho.features.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizGrade : AppCompatActivity() {
    private lateinit var binding: ActivityQuizGradeBinding
    private val viewModel: QuizViewModel by viewModels()
    private var idQuiz: String = "ba6309d9-aa93-49ca-909b-d30fc5d2a06f"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuizGradeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.root.applySystemWindowInsets()
        getQuizGrade(idQuiz)
    }
    private fun View.applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getQuizGrade(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getQuizGrade(id).observe(this@QuizGrade){
                when(it){
                    is Result.Success -> {
                        binding.apply {
                            tvJudulQuiz.text = it.data.data.quiz[0].nama
                            tvQuizGrade.text = it.data.data.quiz[0].nilai.toString()
                            tvJumlahBenar.text = "${it.data.data.quiz[0].jumlahBenar.toString()} Soal"
                            tvJumlahSalah.text = "${it.data.data.quiz[0].jumlahSalah.toString()} Soal"
                            if (it.data.data.quiz[0].lulus){
                                binding.apply {
                                    cvQuizGrade.setCardBackgroundColor(Color.parseColor("#90BEC7"))
                                    tvKalimatPenyemangat.text = "Kamu Hebat!!!"
                                    tvKalimatPenyemangat2.text = "Tetap Pertahankan\nSemangatmu Ya"
                                    tvQuizGrade.setTextColor(Color.parseColor("#015869"))
                                    ivQuizSuccess.setImageResource(R.drawable.ic_quiz_success)
                                }
                            } else {
                                binding.apply {
                                    cvQuizGrade.setCardBackgroundColor(Color.parseColor("#CE9589"))
                                    tvKalimatPenyemangat.text = "Tetap Semangat!!!"
                                    tvKalimatPenyemangat2.text = "Terus Semangat\nKamu Pasti Bisa"
                                    tvQuizGrade.setTextColor(Color.parseColor("#B40101"))
                                    ivQuizSuccess.setImageResource(R.drawable.ic_quiz_fail)
                                }
                            }
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