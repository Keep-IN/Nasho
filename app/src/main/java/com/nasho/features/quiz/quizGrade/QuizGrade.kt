package com.nasho.features.quiz.quizGrade

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
    private var idQuiz: String = "a2011811-212a-4d15-a415-3fb7e437b432"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuizGradeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.root.applySystemWindowInsets()
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