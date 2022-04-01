package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository.CodeRepository

class DeleteCode(
    private val repository: CodeRepository
) {
    suspend operator fun invoke(code: Code){
        repository.deleteCode(code)
    }
}