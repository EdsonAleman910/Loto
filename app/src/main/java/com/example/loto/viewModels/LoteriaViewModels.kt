package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModels : ViewModel() {
    private val  _lotoNumbers = mutableStateOf(emptyList<Int>())

    var lotoNumbers: State<List<Int>> = _lotoNumbers

    var isLoading by mutableStateOf(false)
        private set

    var bolita = 1


    fun fetchData(){
        viewModelScope.launch {
            try {
                isLoading = true
                generarLista()

            }catch (e:Exception){
                println("Error: ${e.message}")
            }finally {
                isLoading = false
            }
        }
    }

    fun generateLotoNumbers(){
        _lotoNumbers.value = (1..60).shuffled().take(6).sorted()
    }


   private suspend fun generarLista() {

       val generatedNumbers = mutableListOf<Int>()
       for (i in 1..6) {
           val number = (1..60).shuffled().first()
           generatedNumbers.add(number)
           _lotoNumbers.value = generatedNumbers.toList().sorted()
           delay(2000)
           bolita++
       }
       bolita = 1
   }




}