package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodesViewModel @Inject constructor(
    private val codeUseCases: CodeUseCases
):ViewModel() {
    
    private val _codes = MutableSharedFlow<List<Code>>()
    val codes: SharedFlow<List<Code>> = _codes

    private var getCodesJob: Job? = null

    init {
        getCodes()
    }

    private fun getCodes(){
        getCodesJob?.cancel()
        getCodesJob = codeUseCases.getCodes().onEach {
            _codes.emit(it)
        }.launchIn(viewModelScope)
    }
}