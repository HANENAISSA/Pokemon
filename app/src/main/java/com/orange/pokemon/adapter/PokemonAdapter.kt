package com.orange.pokemon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.orange.pokemon.R
import com.orange.pokemon.data.PokemonEntity


class PokemonAdapter(private val pokemonsList: List<PokemonEntity>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_item, parent, false)
        return PokemonHolder(v)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bindItems(pokemonsList[position])
    }

    override fun getItemCount() = pokemonsList.size

    class PokemonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(pokemon: PokemonEntity) {
            val textViewName = itemView.findViewById(R.id.name) as TextView
            val textViewcategory = itemView.findViewById(R.id.category) as TextView
            val textViewAttack = itemView.findViewById(R.id.attack) as TextView
            val textViewDefence = itemView.findViewById(R.id.defense) as TextView
            val imgPokemonView = itemView.findViewById(R.id.imageView) as ImageView

            textViewName.text = pokemon.name
            textViewcategory.text = pokemon.category
            textViewAttack.text = pokemon.attack.toString()
            textViewDefence.text = pokemon.defense.toString()

            Glide.with(imgPokemonView)
                .load(pokemon.imageurl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_android_black_24dp)
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imgPokemonView)
        }
    }

}

