package com.jem.blurimageswitcher

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap


class BlurImageSwitcher : ImageSwitcher {

    var scaleFactor = DEFAULT_SCALE_FACTOR
    var blurFactor = DEFAULT_BLUR_FACTOR
    var animationDuration = DEFAULT_ANIMATION_DURATION

    constructor(context: Context?) : super(context) {
        initFallbackAnimations()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BlurImageSwitcher,
            0, 0
        ).apply {
            try {
                animationDuration = getInt(
                    R.styleable.BlurImageSwitcher_animationDuration,
                    DEFAULT_ANIMATION_DURATION.toInt()
                ).toLong()
                blurFactor = getFloat(R.styleable.BlurImageSwitcher_blurFactor, DEFAULT_BLUR_FACTOR)
                scaleFactor =
                    getFloat(R.styleable.BlurImageSwitcher_scaleFactor, DEFAULT_SCALE_FACTOR)
            } finally {
                recycle()
            }
        }
        initFallbackAnimations()
    }

    private fun initFallbackAnimations() {
        BlurUtil.initializeRenderScript(context)
        setInAnimation(context, R.anim.scale_fade_in)
        setOutAnimation(context, R.anim.scale_fade_out)
    }

    override fun showNext() {
        val nextImageView = nextView as ImageView?
        val nextDrawable = nextImageView?.drawable?.toBitmap()
        if (nextImageView != null && nextDrawable != null) {
            val inBlurAnimation =
                BlurAnimation(nextImageView, nextDrawable, scaleFactor, blurFactor, 0f)
            inBlurAnimation.duration = animationDuration
            inAnimation = inBlurAnimation
        }

        val currentImageView = currentView as ImageView?
        val currentDrawable = currentImageView?.drawable?.toBitmap()
        if (currentImageView != null && currentDrawable != null) {
            val outBlurAnimation =
                BlurAnimation(currentImageView, currentDrawable, scaleFactor, 0f, blurFactor)
            outBlurAnimation.duration = animationDuration
            outAnimation = outBlurAnimation
        }

        super.showNext()
    }

    companion object {
        const val DEFAULT_BLUR_FACTOR = 100F
        const val DEFAULT_ANIMATION_DURATION = 400L
        const val DEFAULT_SCALE_FACTOR = 1.2f
    }
}
