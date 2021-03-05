package com.orange.pokemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tab_pokemon")

data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    val imageurl: String,
    val name: String,
    val category: String,
    val attack: Int,
    val defense: Int,
    // val height: String

)