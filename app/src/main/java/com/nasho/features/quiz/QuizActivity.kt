package com.nasho.features.quiz

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewModelScope
import com.core.data.network.Result
import com.core.data.reqres.quiz.quizAccessRequest.QuizAccessRequest
import com.nasho.R
import com.nasho.databinding.ActivityQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuizBinding.inflate(layoutInflater)
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
    private fun userAccessQuiz(id: Int, body: QuizAccessRequest){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.postAccessQuiz(id, body).observe(this@QuizActivity){
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

    private fun getQuiz(id: Int){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getQuiz(id).observe(this@QuizActivity){
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

    private fun getQuizGrade(id: Int){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getQuizGrade(id).observe(this@QuizActivity){
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

    private fun getDiscussion(id: String){
        viewModel.viewModelScope.launch(Dispatchers.Main){
            viewModel.getQuizDiscussion(id).observe(this@QuizActivity){
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