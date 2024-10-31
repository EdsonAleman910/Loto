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

    //Este ya no se usa
    fun generateLotoNumbers(){
        _lotoNumbers.value = (1..60).shuffled().take(6).sorted()
    }

   //Funcion para generar la lista un elemento a la vez
   private suspend fun generarLista() {

       val generatedNumbers = mutableListOf<Int>() //Crea una lista Mutable
       for (i in 1..6) {
           val number = (1..60).shuffled().first() //Se guarda en la variable number
           generatedNumbers.add(number)             //metodo para añadir a la lista el numero generado
           _lotoNumbers.value = generatedNumbers.toList().sorted() //Metodo para guardar en _lotoNumbers la lista ordenada
           delay(2000) //delay de 2 segundos
           bolita++ //aumenta por uno la varible bolita que solo muestra el numero de numeros que se han generado
       }
       bolita = 1 //regresa a 1 la variable para que la proxima vez que se mande a llamar la función comience en 1
   }




}