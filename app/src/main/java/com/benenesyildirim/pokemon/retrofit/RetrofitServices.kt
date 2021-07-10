package com.benenesyildirim.pokemon.retrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices {

    @GET("8912aa29d7c4a5fbf03993b32916d601/raw/681ef0b793ab444f2d81f04f605037fb44814125/pokemon.json")
    suspend fun getAllPokemon(): Response<List<DataModels.PokemonResponseModel>>

    companion object{
        var retrofitServices: RetrofitServices? = null
        fun getInstance(): RetrofitServices {
            if (retrofitServices == null){
                val retrofit = Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/DavidCorrado/")
                    .addConverterFactory(GsonConverterFactory.create()).build()

                retrofitServices = retrofit.create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }
}