package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository.CodeRepository

class GetCodeById(
    private val repository: CodeRepository
) {

    suspend operator fun invoke(id:Int):Code?{
        return repository.getCodeById(id)
    }
}