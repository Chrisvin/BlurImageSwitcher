package com.jem.blurimageswitcherdemo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {
    private var imageIndex = 0
    private var timer: Timer? = null

    private var isUserInteracting = false

    private val imageResourceList = arrayListOf(
        R.drawable.ic_undraw_black_lives_matter_rndk,
        R.drawable.ic_undraw_content_team_3epn,
        R.drawable.ic_undraw_accept_tasks_po1c,
        R.drawable.ic_undraw_feeling_blue_4b7q,
        R.drawable.ic_undraw_smiley_face_lmgm
    )

    override fun onUserInteraction() {
        super.onUserInteraction()
        isUserInteracting = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            arrayListOf("Image 1", "Image 2", "Image 3", "Image 4", "Image 5")
        )
        imageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Don't care
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isUserInteracting) {
                    imageSwitcher.setImageResource(imageResourceList[position])
                }
            }
        }

        startAutoChange()
        autoChangeButton.setOnClickListener {
            if (timer == null) {
                startAutoChange()
                autoChangeButton.text = "Stop autochange"
            } else {
                stopAutoChange()
                autoChangeButton.text = "Start autochange"
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

    private fun startAutoChange() {
        timer = Timer()
        timer?.scheduleAtFixedRate(1000, 1500) {
            runOnUiThread {
//                    imageSwitcher.showNext()
                imageSwitcher.setImageResource(imageResourceList[imageIndex])
                imageIndex = (imageIndex + 1) % 5
            }
        }
    }

    private fun stopAutoChange() {
        timer?.cancel()
        timer = null
    }
}
