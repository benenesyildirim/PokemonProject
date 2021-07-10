package com.benenesyildirim.pokemon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.benenesyildirim.pokemon.R
import com.benenesyildirim.pokemon.adapters.PokemonListAdapter
import com.benenesyildirim.pokemon.retrofit.RetrofitHelper
import com.benenesyildirim.pokemon.retrofit.RetrofitServices
import com.benenesyildirim.pokemon.viewmodel.ListingViewModel
import com.benenesyildirim.pokemon.viewmodel.MyViewModelFactory

class ListingPage : Fragment() {
    lateinit var viewModel: ListingViewModel
    private val adapter = PokemonListAdapter()
    private lateinit var pokemonRv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listing_page, container, false)
        val retrofitServices = RetrofitServices.getInstance()
        val retrofitHelper = RetrofitHelper(retrofitServices)

        pokemonRv = view.findViewById(R.id.pokemon_list_rv)
        pokemonRv.adapter = adapter

        setViewModel(retrofitHelper)

        (activity as AppCompatActivity).supportActionBar?.title = "Pokemon"
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        return view
    }

    private fun setViewModel(retrofitHelper: RetrofitHelper) {
        viewModel = ViewModelProvider(this,MyViewModelFactory(retrofitHelper)).get(ListingViewModel::class.java)

        viewModel.pokemonList.observe(viewLifecycleOwner,{
            adapter.setPokemons(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner,{
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.getAllPokemons()
    }
}