package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code

sealed class AddEditCodeEvent {
    object SaveCode : AddEditCodeEvent()
    data class ShowSnackbar(val message: String) : AddEditCodeEvent()
}