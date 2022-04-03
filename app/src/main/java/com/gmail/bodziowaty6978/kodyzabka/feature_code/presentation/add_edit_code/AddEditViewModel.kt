package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val useCases:CodeUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){



}