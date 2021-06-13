package com.anasLub.a120181440

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var pv: PlayerView? = null
    var p: SimpleExoPlayer? = null
    var playWhenReady = true
    var currentWindow = 0
    var playpackPosition: Long = 0

    val L1 = "https://firebasestorage.googleapis.com/v0/b/project-72745.appspot.com/o/1%20MINUTE%20OF%20ITALY%20-%20TRAVEL%20VIDEO.mkv?alt=media&token=e7616498-0c35-41d8-b0c9-22b382f25199"
    val L2 = "https://firebasestorage.googleapis.com/v0/b/project-72745.appspot.com/o/1%20Minute%20Video%20-%20Doggie.mkv?alt=media&token=b613f342-54c8-4597-8a51-36efa3c7fd38"
    val L3 = "https://firebasestorage.googleapis.com/v0/b/project-72745.appspot.com/o/Best%20Short%20Motivational%20Speech%20Video%20-%2024%20HOURS%20-%201-Minute%20Motivation%20%232.mkv?alt=media&token=f5ab5b79-8697-4efd-a943-2d69eb8bb62d"
    val L4 = "https://firebasestorage.googleapis.com/v0/b/project-72745.appspot.com/o/1%20Minute%20Electronic%20Music%20!%20No%20Copyright%20Music%20by%20Mukesh%20!.mkv?alt=media&token=9697381e-f6c9-422e-a7fc-2bc5966ed840"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





       pv = findViewById(R.id.videoView)
        Btn1.setOnClickListener {
        releaseVideo()
        initVideo(L1)
        }

        Btn2.setOnClickListener {
          releaseVideo()
         initVideo(L2)
        }

        Btn3.setOnClickListener {
         releaseVideo()
          initVideo(L3)
        }
        Btn3.setOnClickListener {
           releaseVideo()
         initVideo(L4)
        }


    }

   fun initVideo(L: String = L1) {
        p = ExoPlayerFactory.newSimpleInstance(this)
        pv!!.player = p
        val uri = Uri.parse(L)
        val dataSourceFactory = DefaultDataSourceFactory(this, "exoplayer-codelab")
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        p!!.playWhenReady = playWhenReady
        p!!.seekTo(currentWindow, playpackPosition)
        p!!.prepare(mediaSource, false, false)

    }

    private fun releaseVideo() {

        if (p != null) {
            playWhenReady = p!!.playWhenReady
            playpackPosition = p!!.currentPosition
            currentWindow = p!!.currentWindowIndex
            p!!.stop()
            p = null
        }

    }


    override fun onStart() {
        super.onStart()
        initVideo()
    }

    override fun onPause() {
        super.onPause()
        releaseVideo()
    }

    override fun onResume() {
        super.onResume()
        if (p != null) {
            initVideo()
        }
    }

    override fun onStop() {
        super.onStop()
        releaseVideo()
    }

}