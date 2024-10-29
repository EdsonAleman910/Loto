package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoteriaViewModels : ViewModel() {
    private val  _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    fun generateLotoNumbers(){
        _lotoNumbers.value = (1..60).shuffled().take(6).sorted()
    }
}