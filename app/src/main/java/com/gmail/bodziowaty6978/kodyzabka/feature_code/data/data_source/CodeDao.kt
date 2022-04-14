package com.gmail.bodziowaty6978.kodyzabka.feature_code.data.data_source

import androidx.room.*
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import kotlinx.coroutines.flow.Flow

@Dao
interface CodeDao {

    @Query("SELECT * FROM code")
    fun getCodes(): List<Code>

    @Query("SELECT * FROM code")
    fun getCodesFlow(): Flow<List<Code>>

    @Query("SELECT * FROM code WHERE id=:id")
    fun getCodeById(id:Int):Code?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCode(code:Code)

    @Delete
    fun deleteCode(code:Code)
}