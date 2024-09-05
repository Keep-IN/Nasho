package com.nasho.features.Materi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
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
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
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
    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var btnPlayPause: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var btnMute: ImageButton
    private lateinit var btnFullScreen: ImageButton
    private val handler = Handler()
    private var isFullScreen = false
    private var idQuiz: String? = null
    private var idMateri2: String? = null

    private val materiViewModel: MateriViewModel by viewModels()
    private val viewModel: QuizViewModel by viewModels()

    @SuppressLint("ResourceType")
    @OptIn(UnstableApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        player = ExoPlayer.Builder(this).build()

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playerView = binding.playerView

        btnPlayPause = findViewById(R.id.btnPlayPause)
        seekBar = findViewById(R.id.seekBar)
        btnMute = findViewById(R.id.btnMute)
        btnFullScreen = findViewById(R.id.btnFullScreen)

        val tvJudulUtama: TextView = binding.tvJudulUtama
        val tvJudulSpekMateri: TextView = binding.tvJudulSpekMateri
        val tvKategoriMateri: TextView = binding.tvKategoriMateri
        val tvIsiSpekMateri: TextView = binding.tvIsiSpekMateri

        val idMateri = intent.getStringExtra("idMateri")
        Log.d("idMateri", idMateri.toString())
        if (idMateri != null) {
            materiViewModel.getSpecificMateri(idMateri).observe(this, Observer { result ->
                when (result) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        val materi = result.data.data.firstOrNull()
                        if (materi != null) {
                            val videoLink = materi.linkvideo
                            initializePlayer(videoLink)
                            tvJudulUtama.text = materi.kategori
                            tvJudulSpekMateri.text = materi.judul
                            tvKategoriMateri.text = materi.kategori
                            tvIsiSpekMateri.text = materi.isi
                            idMateri2 = materi.id
                            idQuiz = materi.idQuiz
                        }
                    }
                    is Result.Error -> {
                    }
                }
            })
        }

        btnPlayPause.setOnClickListener {
            if (player.isPlaying) {
                player.pause()
                btnPlayPause.setImageResource(R.drawable.ic_play)
            } else {
                player.play()
                btnPlayPause.setImageResource(R.drawable.ic_pause)
            }
        }

        btnMute.setOnClickListener {
            player.volume = if (player.volume == 0f) 1f else 0f
            val muteDrawable = if (player.volume == 0f) R.drawable.ic_mute else R.drawable.ic_unmute
            btnMute.setImageResource(muteDrawable)
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player.seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                super.onPlaybackStateChanged(state)
                if (state == Player.STATE_READY) {
                    updateSeekBarProgress()
                }
            }
        })

        btnFullScreen.setOnClickListener {
            if (isFullScreen) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                btnFullScreen.setImageResource(R.drawable.ic_fullscreen)
            } else {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                btnFullScreen.setImageResource(R.drawable.ic_exit_fullscreen)
            }
            isFullScreen = !isFullScreen
        }

        binding.btnNextToQuiz.setOnClickListener {
            viewModel.viewModelScope.launch(Dispatchers.Main){
                idQuiz?.let { it1 ->
                    viewModel.postAccessQuiz(it1).observe(this@MateriVideo){
                        when(it){
                            is Result.Success -> {
                                startActivity(Intent(this@MateriVideo, QuizActivity::class.java).apply {
                                    putExtra("idMengambilQuiz", it.data.data.idMengambilQuiz[0].id)
                                    putExtra("idMateri", idMateri2)
                                    putExtra("idQuiz", idQuiz)
                                })
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
    }

    @OptIn(UnstableApi::class)
    private fun initializePlayer(videoLink: String) {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem = MediaItem.fromUri(videoLink)

        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(this)
        ).createMediaSource(mediaItem)
        player.setMediaSource(mediaSource)
        player.prepare()
        player.playWhenReady = true

        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                Log.e("PlayerError", "Error playing video: ${error.message}")
            }
        })
    }

    override fun onStop() {
        super.onStop()
        player.stop()
        handler.removeCallbacksAndMessages(null)
    }

    private fun updateSeekBarProgress() {
        seekBar.max = player.duration.toInt()
        seekBar.progress = player.currentPosition.toInt()
        handler.postDelayed({ updateSeekBarProgress() }, 100)
    }
}
