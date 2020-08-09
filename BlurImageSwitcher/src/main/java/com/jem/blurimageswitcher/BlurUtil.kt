package com.jem.blurimageswitcher

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


object BlurUtil {

    private var renderScript: RenderScript? = null

    fun initializeRenderScript(context: Context) {
        renderScript = RenderScript.create(context.applicationContext)
    }

    fun scaledBitmapBlur(bitmap: Bitmap, factor: Int): Bitmap {
        return if (factor <= 0 || bitmap.width / factor <= 0 || bitmap.height / factor <= 0) {
//            Bitmap.createBitmap(
//                1,
//                1,
//                Bitmap.Config.ARGB_8888
//            )
            bitmap
        } else {
            Bitmap.createScaledBitmap(
                bitmap,
                bitmap.width / factor,
                bitmap.height / factor,
                true
            )
        }
    }

    fun renderscriptBlur(bitmap: Bitmap, factor: Float): Bitmap {
        return if (renderScript == null || factor <= 0) {
            bitmap
        } else {
            var input: Allocation? = null
            var output: Allocation? = null
            var blurScript: ScriptIntrinsicBlur? = null
            try {
                input = Allocation.createFromBitmap(
                    renderScript,
                    bitmap,
                    Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_GRAPHICS_TEXTURE
                )
                output = Allocation.createTyped(renderScript, input.type)
                blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
                blurScript.setRadius(factor.coerceAtMost(25f))
                blurScript.setInput(input)
                blurScript.forEach(output)
                output.copyTo(bitmap)
            } finally {
                input?.destroy()
                output?.destroy()
                blurScript?.destroy()
            }
            return bitmap
        }
    }

        }
    }
}
