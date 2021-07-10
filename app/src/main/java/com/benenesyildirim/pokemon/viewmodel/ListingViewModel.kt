package com.benenesyildirim.pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.benenesyildirim.pokemon.retrofit.DataModels
import com.benenesyildirim.pokemon.retrofit.RetrofitHelper
import kotlinx.coroutines.*

class ListingViewModel constructor(private val retrofitHelper: RetrofitHelper) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val pokemonList = MutableLiveData<List<DataModels.PokemonResponseModel>>()
    val loading = MutableLiveData<Boolean>()

    var job: Job? = null

    fun getAllPokemons(){
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitHelper.getAllPokemon()

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    pokemonList.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Something went wrong...")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}