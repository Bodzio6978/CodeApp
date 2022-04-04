package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Code(
    val code:String,
    val timeStamp:Long,
    val user:String,
    @PrimaryKey val id:Int? = null
):Parcelable

class InvalidCodeException(message:String):Exception(message)
