package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.model.Code
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.CodeUseCases
import com.gmail.bodziowaty6978.kodyzabka.feature_code.domain.use_case.DeleteCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodesListViewModel @Inject constructor(
    private val useCases:CodeUseCases
): ViewModel(){

    private val _codes = MutableSharedFlow<List<Code>>()
    val codes: SharedFlow<List<Code>> = _codes

    init {
        getCodes()
    }

    fun onEvent(codeEvent: CodeEvent){
        when(codeEvent){
            is CodeEvent.DeleteCode -> {
                viewModelScope.launch {
                    useCases.deleteCode(codeEvent.code)
                }
            }
            is CodeEvent.EditCode -> {

            }
        }
    }

    private fun getCodes(){
        viewModelScope.launch {
            useCases.getCodes().collect { codes ->
                codes.sortedByDescending { code ->
                    code.timeStamp
                }
                _codes.emit(codes)
            }
        }
    }

}