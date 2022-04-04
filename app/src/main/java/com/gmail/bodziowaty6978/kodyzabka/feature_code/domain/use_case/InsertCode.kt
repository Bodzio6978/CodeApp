package com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case

import com.gmail.bodziowaty6978.kodyzabka.R
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.InvalidCodeException
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.repository.CodeRepository
import com.gmail.bodziowaty6978.kodyzabka.util.ResourceProvider

class InsertCode(
    private val repository: CodeRepository,
    private val resourceProvider: ResourceProvider
) {
    @Throws(InvalidCodeException::class)
    suspend operator fun invoke(code:Code){
        if (code.code.isBlank()){
            throw InvalidCodeException(resourceProvider.getString(R.string.kod_nie_moze_byc_pusty))
        }
        if (code.code.length!=12){
            throw InvalidCodeException(resourceProvider.getString(R.string.kod_powinien_miec_12_cyfer))
        }
        if (!code.code.matches(Regex("[0-9]+"))){
            throw InvalidCodeException(resourceProvider.getString(R.string.kod_powinien_skladac_sie_z_samych_cyfer))
        }
        repository.insertCode(code)
    }
}