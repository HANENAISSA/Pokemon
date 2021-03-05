package com.orange.pokemon.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("SELECT * FROM tab_pokemon")
    suspend fun getALLPokemons() : List<PokemonEntity>


    @Insert
    suspend fun insertAllPokemons(entities: List<PokemonEntity>)
}
