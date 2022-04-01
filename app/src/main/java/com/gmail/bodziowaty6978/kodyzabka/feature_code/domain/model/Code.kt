package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Code(
    val code:String,
    val timeStamp:Long,
    val user:String,
    @PrimaryKey val id:Int? = null
)
