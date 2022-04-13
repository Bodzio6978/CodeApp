package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.DeleteCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodesListViewModel @Inject constructor(
    private val useCases: CodeUseCases
) : ViewModel() {

    private val _codes = MutableSharedFlow<List<Code>>()
    val codes: SharedFlow<List<Code>> = _codes

    val _uiState = Channel<UiEvent>()
//    val uiState = _uiState.receiveAsFlow()

    private var lastDeletedCode: Code? = null

    fun onEvent(codeEvent: CodeEvent) {
        when (codeEvent) {
            is CodeEvent.DeleteCode -> {
                val code = codeEvent.code
                lastDeletedCode = code
                viewModelScope.launch(Dispatchers.IO) {
                    useCases.deleteCode(code)
                    _uiState.send(UiEvent.DeletedCode(code.id!!))
                }
            }
            is CodeEvent.EditCode -> {

            }

            is CodeEvent.RestoreCode -> {
                viewModelScope.launch(Dispatchers.IO) {
                    lastDeletedCode?.let { useCases.insertCode(it) }
                }
            }
        }
    }

    fun getCodes() {
        viewModelScope.launch(Dispatchers.IO) {
            val codes = useCases.getCodes()
            _codes.emit(codes)
        }
    }

}