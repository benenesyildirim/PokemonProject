package com.benenesyildirim.pokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benenesyildirim.pokemon.retrofit.RetrofitHelper

class MyViewModelFactory constructor(private val retrofitHelper: RetrofitHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListingViewModel::class.java)) {
            ListingViewModel(this.retrofitHelper) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}