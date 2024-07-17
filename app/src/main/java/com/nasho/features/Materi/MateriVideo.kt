package com.nasho.features.Materi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nasho.R
import com.nasho.databinding.ActivityMateriVideoBinding
import com.nasho.features.login.Login

class MateriVideo : AppCompatActivity() {
    private lateinit var binding: ActivityMateriVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load PlayerFragment into the container
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.playerFragmentContainer, PlayerFragment())
                .commit()
        }

        binding.ivBackVideo.setOnClickListener {
            startActivity(Intent(this@MateriVideo, Login::class.java))
        }
    }
}
