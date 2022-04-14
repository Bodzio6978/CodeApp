package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import kotlinx.coroutines.flow.Flow

interface CodeRepository {

    suspend fun getCodes():List<Code>

    fun getCodesFlow():Flow<List<Code>>

    suspend fun getCodeById(id:Int):Code?

    suspend fun insertCode(code:Code)

    suspend fun deleteCode(code:Code)

}