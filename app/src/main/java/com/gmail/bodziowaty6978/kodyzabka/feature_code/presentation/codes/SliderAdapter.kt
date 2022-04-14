package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer

class SliderAdapter(private val codesList: MutableList<Code>) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val codeBitmap: ImageView = view.findViewById(R.id.ivCode)
        val codeOwner: TextView = view.findViewById(R.id.tvCodeOwner)
        val code: TextView = view.findViewById(R.id.tvCode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.code_layout, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val codeItem = codesList[position]
        holder.code.text = codeItem.code
        holder.codeOwner.text = codeItem.user
        holder.codeBitmap.setImageBitmap(
            generateCodeBitmap(codeItem.code)
        )
    }

    override fun getItemCount(): Int = codesList.size

    private fun generateCodeBitmap(
        barcodeValue: String,
        @ColorInt barcodeColor: Int = Color.BLACK,
        @ColorInt backgroundColor: Int = Color.TRANSPARENT,
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
}