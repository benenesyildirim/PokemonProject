package com.benenesyildirim.pokemon.retrofit

class RetrofitHelper constructor(private val retrofitServices: RetrofitServices) {

    suspend fun getAllPokemon() = retrofitServices.getAllPokemon()
}