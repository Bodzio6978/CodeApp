package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository.CodeRepository
import kotlinx.coroutines.flow.Flow

class GetCodesFlow(
    private val repository: CodeRepository
){

    operator fun invoke():Flow<List<Code>>{
        return repository.getCodesFlow()
    }
}