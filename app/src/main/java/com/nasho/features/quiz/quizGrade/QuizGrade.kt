package com.nasho.features.quiz.quizGrade

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nasho.R
import com.nasho.databinding.ActivityQuizGradeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizGrade : AppCompatActivity() {
    private lateinit var binding: ActivityQuizGradeBinding
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
}