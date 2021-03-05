package com.orange.pokemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PokemonEntity::class],
    version = 1
)

abstract class PokemonDB : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {

        private var instance: PokemonDB? = null

        fun getInstance(context: Context): PokemonDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    PokemonDB::class.java,
                    "pokemon_db"
                ).build()
            }
            return instance!!
        }
    }
}