package com.jem.blurimageswitcher

import android.graphics.Bitmap
import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView

class BlurAnimation constructor(
    private val imageView: ImageView,
    private val bitmap: Bitmap,
    private val scale: Float,
    private val startValue: Float,
    private val endValue: Float
) : Animation() {

    private val difValue: Float = endValue - startValue

    override fun applyTransformation(
        interpolatedTime: Float,
        t: Transformation?
    ) {
        super.applyTransformation(interpolatedTime, t)
        if (interpolatedTime > 0.98) {
            // In the final animation frames, reset to the original image,
            // so that it can be used for future blur animations
            imageView.setImageBitmap(bitmap)
        } else {
            val current = (difValue * interpolatedTime + startValue + 0.5f).toInt()
            val blurred = BlurUtil.scaledBitmapBlur(bitmap, current)
            imageView.setImageBitmap(blurred)
        }
        // Stop value being greater than start value indicates that it's a view going out animation
        imageView.alpha = if (endValue - startValue > 0) {
            // Fade out
            1f - interpolatedTime
        } else {
            // Fade in
            interpolatedTime
        }
        val scaleFactor = if (endValue - startValue > 0) {
            // Scale out
            1f + interpolatedTime * (1f - scale)
        } else {
            // Scale in
            scale - interpolatedTime * (1f - scale)
        }
        imageView.scaleX = scaleFactor
        imageView.scaleY = scaleFactor
    }

}