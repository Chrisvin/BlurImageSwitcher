package com.jem.blurimageswitcherdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {
    private var imageIndex = 0
    private lateinit var timer: Timer

    private val imageResourceList = arrayListOf(
        R.drawable.ic_undraw_black_lives_matter_rndk,
        R.drawable.ic_undraw_content_team_3epn,
        R.drawable.ic_undraw_accept_tasks_po1c,
        R.drawable.ic_undraw_feeling_blue_4b7q,
        R.drawable.ic_undraw_smiley_face_lmgm
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = Timer()
        timer.scheduleAtFixedRate(1000, 1500) {
            runOnUiThread {
//                    imageSwitcher.showNext()
                imageSwitcher.setImageResource(imageResourceList[imageIndex])
                imageIndex = (imageIndex + 1) % 5
            }
        }

//        val currentImageView = (imageSwitcher.currentView as ImageView)
//        val currentBitmap = currentImageView.drawable.toBitmap()
//        val outBlurAnimation = BlurAnimation(currentImageView, currentBitmap, 100, 0)
//        outBlurAnimation.duration =
//            this.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
//        currentImageView.animation = outBlurAnimation
//        currentImageView.animate()
    }
}
