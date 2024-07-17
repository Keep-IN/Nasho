package com.nasho.features.Materi

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.nasho.R

class PlayerFragment : Fragment() {
    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var btnPlayPause: ImageButton
    private lateinit var seekBar: SeekBar
    private lateinit var btnMute: ImageButton
    private lateinit var btnFullScreen: ImageButton
    private val handler = Handler()

    private var isFullScreen = false

    @OptIn(UnstableApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)

        playerView = view.findViewById(R.id.playerView)
        btnPlayPause = view.findViewById(R.id.btnPlayPause)
        seekBar = view.findViewById(R.id.seekBar)
        btnMute = view.findViewById(R.id.btnMute)
        btnFullScreen = view.findViewById(R.id.btnFullScreen)

        player = ExoPlayer.Builder(requireContext()).build()
        playerView.player = player

        val mediaItem = MediaItem.Builder()
            .setUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()

        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(requireContext())
        ).createMediaSource(mediaItem)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true

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
                if (state == ExoPlayer.STATE_READY) {
                    updateSeekBarProgress()
                }
            }
        })

        btnFullScreen.setOnClickListener {
            if (!isFullScreen) {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                isFullScreen = true
                playerView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            } else {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                isFullScreen = false
                playerView.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        return view
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
