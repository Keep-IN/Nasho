package com.nasho.features.quiz.quizDiscussion

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nasho.R
import com.nasho.databinding.ActivityQuizDiscussionBinding

class QuizDiscussion : AppCompatActivity() {
    private lateinit var binding: ActivityQuizDiscussionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityQuizDiscussionBinding.inflate(layoutInflater)
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