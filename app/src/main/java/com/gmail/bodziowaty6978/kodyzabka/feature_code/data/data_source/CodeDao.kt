package com.gmail.bodziowaty6978.kodyzabka.feature_code.data.data_source

import androidx.room.Query
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import kotlinx.coroutines.flow.Flow


interface CodeDao {

    @Query("SELECT * FROM code")
    fun getCodes(): Flow<List<Code>>
}