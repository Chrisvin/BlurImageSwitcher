package com.jem.blurimageswitcher

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap


class BlurImageSwitcher : ImageSwitcher {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun showNext() {
        val nextImageView = nextView as ImageView?
        val nextDrawable = nextImageView?.drawable?.toBitmap()
        if (nextImageView != null && nextDrawable != null) {
            val inBlurAnimation = BlurAnimation(nextImageView, nextDrawable, 100, 0)
            inBlurAnimation.duration =
                resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            inAnimation = inBlurAnimation
        }

        val currentImageView = currentView as ImageView?
        val currentDrawable = currentImageView?.drawable?.toBitmap()
        if (currentImageView != null && currentDrawable != null) {
            val outBlurAnimation = BlurAnimation(currentImageView, currentDrawable, 0, 100)
            outBlurAnimation.duration =
                resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
            outAnimation = outBlurAnimation
        }

        super.showNext()
    }
}
