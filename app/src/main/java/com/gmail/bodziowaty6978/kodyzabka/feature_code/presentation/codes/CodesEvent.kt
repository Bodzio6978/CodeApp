package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code

sealed class CodesEvent {
    data class DeleteNote(val code:Code):CodesEvent()
    object RestoreCode:CodesEvent()
}