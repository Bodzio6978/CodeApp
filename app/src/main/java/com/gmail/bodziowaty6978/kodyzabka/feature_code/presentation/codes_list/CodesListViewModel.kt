package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodesListViewModel @Inject constructor(
    private val useCases: CodeUseCases
) : ViewModel() {

    private val _codes = MutableSharedFlow<List<Code>>()
    val codes: SharedFlow<List<Code>> = _codes

    private var lastDeletedCode: Code? = null

    private var job:Job? = null

    fun onEvent(codeEvent: CodeEvent) {
        when (codeEvent) {
            is CodeEvent.DeleteCode -> {
                val code = codeEvent.code
                lastDeletedCode = code
                viewModelScope.launch(Dispatchers.IO) {
                    useCases.deleteCode(code)
                }
            }
            is CodeEvent.EditCode -> {

            }

            is CodeEvent.RestoreCode -> {
                viewModelScope.launch(Dispatchers.IO) {
                    useCases.insertCode(lastDeletedCode ?: return@launch)
                }
            }
        }
    }

    fun getCodes() {
        job?.cancel()
        job = useCases.getCodesFlow().onEach { codes ->
            _codes.emit(codes)
        }.launchIn(viewModelScope)
    }

}