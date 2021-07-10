package com.benenesyildirim.pokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.benenesyildirim.pokemon.R
import com.bumptech.glide.Glide

class DetailPageFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail_page, container, false)

        val pokemonImage: ImageView = view.findViewById(R.id.pokemon_iv_detail_page)
        val pokemonName: TextView = view.findViewById(R.id.pokemon_description_tv_detail_page)

        pokemonName.text = arguments?.getString("pokemonDescription")
        Glide.with(pokemonImage.context).load(arguments?.getString("pokemonImage")).into(pokemonImage)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.title = arguments?.getString("pokemonName")
            (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        return view
    }
}