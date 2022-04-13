package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code

sealed class CodeEvent {
    data class DeleteCode(val code:Code):CodeEvent()
    data class EditCode(val codeId:Int):CodeEvent()
    object RestoreCode:CodeEvent()
}