package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

sealed class UiEvent {
    data class DeletedCode(val codeId:Int):UiEvent()
}