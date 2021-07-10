package com.benenesyildirim.pokemon.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.benenesyildirim.pokemon.R
import com.benenesyildirim.pokemon.retrofit.DataModels
import com.bumptech.glide.Glide

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    private var pokemonList = mutableListOf<DataModels.PokemonResponseModel>()

    fun setPokemons(movies: List<DataModels.PokemonResponseModel>) {
        this.pokemonList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item_design, parent, false)
        return PokemonViewHolder(v)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.headerTv.text = pokemon.name
        holder.descriptionTv.text = pokemon.description
        Glide.with(holder.itemView.context).load(pokemon.imageUrl).into(holder.pokemonImage)

        setClickListener(pokemon,holder)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    private fun setClickListener(pokemon:DataModels.PokemonResponseModel,holder: PokemonViewHolder){
        val bundle = Bundle()
        bundle.putString("pokemonName", pokemon.name)
        bundle.putString("pokemonDescription", pokemon.description)
        bundle.putString("pokemonImage", pokemon.imageUrl)

        holder.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_listingPage_to_detailPageFragment,
                bundle
            )
        )
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTv: TextView = itemView.findViewById(R.id.pokemon_header_tv_item_design)
        val descriptionTv: TextView = itemView.findViewById(R.id.pokemon_description_tv_item_design)
        val pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_iv_item_design)
    }
}