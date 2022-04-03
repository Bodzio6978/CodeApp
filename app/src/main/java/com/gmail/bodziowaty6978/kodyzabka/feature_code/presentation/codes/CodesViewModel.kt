package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodesViewModel @Inject constructor(
    private val codeUseCases: CodeUseCases
):ViewModel() {

    private val _state = MutableStateFlow(CodesState(codes = emptyList()))
    val state:StateFlow<CodesState> = _state

    private var getCodesJob: Job? = null

    private var lastDeletedCode:Code? = null

    init {
        getCodes()
    }

    private fun getCodes(){
        getCodesJob?.cancel()
        getCodesJob = codeUseCases.getCodes().onEach {
            _state.value = state.value.copy(codes = it)
        }.launchIn(viewModelScope)
    }
}