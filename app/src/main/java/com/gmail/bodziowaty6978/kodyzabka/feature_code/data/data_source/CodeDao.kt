package com.gmail.bodziowaty6978.kodyzabka.feature_code.data.data_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import kotlinx.coroutines.flow.Flow


interface CodeDao {

    @Query("SELECT * FROM code")
    fun getCodes(): Flow<List<Code>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCode(code:Code)

    @Delete
    fun deleteCode(code:Code)
}