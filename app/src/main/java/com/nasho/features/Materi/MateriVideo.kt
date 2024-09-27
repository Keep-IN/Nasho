package com.nasho.features.Materi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import com.core.data.network.Result
import com.nasho.R
import com.nasho.databinding.ActivityMateriVideoBinding
import com.nasho.features.quiz.QuizActivity
import com.nasho.features.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MateriVideo : AppCompatActivity() {
    private lateinit var binding: ActivityMateriVideoBinding
    private lateinit var webView: WebView
    private val handler = Handler()
    private var idQuiz: String? = null
    private var idMateri2: String? = null

    private val materiViewModel: MateriViewModel by viewModels()
    private val viewModel: QuizViewModel by viewModels()

    private var fullscreenContainer: FrameLayout? = null
    private var originalSystemUiVisibility: Int = 0
    private var originalOrientation: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webView

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivBackVideo.setOnClickListener {
            finish()
        }

        val tvJudulSpekMateri: TextView = binding.tvJudulSpekMateri
        val tvKategoriMateri: TextView = binding.tvKategoriMateri
        val tvIsiSpekMateri: TextView = binding.tvIsiSpekMateri

        val idMateri = intent.getStringExtra("idMateri")
        if (idMateri != null) {
            materiViewModel.getSpecificMateri(idMateri).observe(this, Observer { result ->
                when (result) {
                    is Result.Loading -> {
                        // Handle loading state
                    }
                    is Result.Success -> {
                        val materi = result.data.data.firstOrNull()
                        if (materi != null) {
                            val videoLink = materi.linkvideo
                            initializeWebView(videoLink)
                            tvJudulSpekMateri.text = materi.judul
                            tvKategoriMateri.text = materi.subjudul
                            tvIsiSpekMateri.text = materi.isi
                            idMateri2 = materi.id
                            idQuiz = materi.idQuiz
                        }
                    }
                    is Result.Error -> {
                        // Handle error state
                    }
                }
            })
        }

            binding.btnNextToQuiz.setOnClickListener {
                viewModel.viewModelScope.launch(Dispatchers.Main) {
                    idQuiz?.let { it1 ->
                        viewModel.postAccessQuiz(it1).observe(this@MateriVideo) {
                            when (it) {
                                is Result.Success -> {
                                    startActivity(Intent(this@MateriVideo, QuizActivity::class.java).apply {
                                        putExtra("idMengambilQuiz", it.data.data.idMengambilQuiz[0].id)
                                        putExtra("idMateri", idMateri2)
                                        putExtra("idQuiz", idQuiz)
                                    })
                                }
                                is Result.Error -> {
                                    // Handle error
                                }
                                else -> {
                                    // Handle other states
                                }
                            }
                        }
                    }
                }
            }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeWebView(videoLink: String) {
        webView.settings.apply {
            javaScriptEnabled = true
            mediaPlaybackRequiresUserGesture = false
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowCustomView(view: View, callback: CustomViewCallback) {
                if (fullscreenContainer == null) {
                    fullscreenContainer = FrameLayout(this@MateriVideo)
                    val decorView = window.decorView as FrameLayout
                    decorView.addView(fullscreenContainer, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
                }

                fullscreenContainer?.apply {
                    visibility = View.VISIBLE
                    removeAllViews()
                    addView(view)
                }

                originalSystemUiVisibility = window.decorView.systemUiVisibility
                originalOrientation = requestedOrientation

                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onHideCustomView() {
                fullscreenContainer?.apply {
                    visibility = View.GONE
                    removeAllViews()
                }

                window.decorView.systemUiVisibility = originalSystemUiVisibility
                requestedOrientation = originalOrientation
            }
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("WebView", "Page loaded: $url")
            }
        }
        webView.loadUrl(videoLink)
    }

    override fun onStop() {
        super.onStop()
        webView.loadUrl("about:blank") // Clear WebView content
        handler.removeCallbacksAndMessages(null)
    }
}