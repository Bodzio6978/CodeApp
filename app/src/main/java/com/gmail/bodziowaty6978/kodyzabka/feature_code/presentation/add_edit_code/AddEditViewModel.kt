package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.add_edit_code

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import com.gmail.bodziowaty6978.kodyzabka.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val useCases:CodeUseCases,
): ViewModel(){

    private val _codeEventState = MutableSharedFlow<AddEditCodeEvent>()
    val codeEventState: SharedFlow<AddEditCodeEvent> = _codeEventState

    fun saveCode(code: Code){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCases.insertCode(code)
                _codeEventState.emit(AddEditCodeEvent.SaveCode)
            }catch (e:Exception){
                _codeEventState.emit(AddEditCodeEvent.ShowSnackbar(
                    message = e.message ?: "Couldn't save the code"
                ))
            }
        }
    }
}



