package com.gmail.bodziowaty6978.kodyzabka.util

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val context:Context) {

    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}