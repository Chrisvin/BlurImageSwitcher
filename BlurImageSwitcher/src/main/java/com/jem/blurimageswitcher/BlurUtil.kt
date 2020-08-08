package com.jem.blurimageswitcher

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur


object BlurUtil {

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

    fun renderscriptBlur(context: Context, bitmap: Bitmap, factor: Int): Bitmap {
        return if (factor <=0) {
            bitmap
        } else {
            TODO("Handle renderscript blur handling")
        }
    }
}
