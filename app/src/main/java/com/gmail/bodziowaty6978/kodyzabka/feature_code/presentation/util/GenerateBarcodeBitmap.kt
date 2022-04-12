package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.util

import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer

fun RecyclerView.ViewHolder.generateCodeBitmap(
    barcodeValue: String,
    @ColorInt barcodeColor: Int = Color.WHITE,
    @ColorInt  backgroundColor: Int = Color.TRANSPARENT,
    widthPixels: Int = 800,
    heightPixels: Int = 200
):Bitmap{
    val bitMatrix = Code128Writer().encode(
        barcodeValue,
        BarcodeFormat.CODE_128,
        widthPixels,
        heightPixels
    )

    val pixels = IntArray(bitMatrix.width * bitMatrix.height)
    for (y in 0 until bitMatrix.height) {
        val offset = y * bitMatrix.width
        for (x in 0 until bitMatrix.width) {
            pixels[offset + x] =
                if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
        }
    }

    val bitmap = Bitmap.createBitmap(
        bitMatrix.width,
        bitMatrix.height,
        Bitmap.Config.ARGB_8888
    )
    bitmap.setPixels(
        pixels,
        0,
        bitMatrix.width,
        0,
        0,
        bitMatrix.width,
        bitMatrix.height
    )
    return bitmap
}
