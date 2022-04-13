package com.gmail.bodziowaty6978.kodyzabka.feature_code.data.repository

import android.util.Log
import com.gmail.bodziowaty6978.kodyzabka.feature_code.data.data_source.CodeDao
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository.CodeRepository
import kotlinx.coroutines.flow.Flow

class CodeRepositoryImp(
    private val dao:CodeDao
):CodeRepository {

    override fun getCodes(): List<Code> {
        return dao.getCodes()
    }

    override suspend fun getCodeById(id: Int): Code? {
        return dao.getCodeById(id)
    }

    override suspend fun insertCode(code: Code) {
        dao.insertCode(code)
    }

    override suspend fun deleteCode(code: Code) {
        dao.deleteCode(code)
    }
}