package com.gmail.bodziowaty6978.kodyzabka.feature_code.presentation.codes

import android.util.Log
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

    fun updateCode(code:Code){
        viewModelScope.launch(Dispatchers.IO) {
            codeUseCases.insertCode(code)
        }
    }

    fun getCodes(){
        viewModelScope.launch(Dispatchers.IO) {
            val codes = codeUseCases.getCodes().toMutableList()
            codes.sortBy { it.timeStamp }
            _codes.emit(codes)
        }
    }
}