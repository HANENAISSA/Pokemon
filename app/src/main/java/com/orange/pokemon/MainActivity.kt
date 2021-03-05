package com.orange.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orange.pokemon.adapter.PokemonAdapter
import com.orange.pokemon.data.PokemonDB
import com.orange.pokemon.data.PokemonDao
import com.orange.pokemon.data.PokemonEntity
import com.orange.pokemon.model.Pokemon
import com.orange.pokemon.networking.ApiService
import com.orange.pokemon.networking.NetworkClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var db: PokemonDB
    private lateinit var pokemonDao: PokemonDao
    private lateinit var pokemons: ArrayList<Pokemon>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_main)
        val service = NetworkClient().getRetrofit().create(ApiService::class.java)

        //RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.pokemon_recycler)



        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        db = PokemonDB.getInstance(this)
        pokemonDao = db.getPokemonDao()

        service.getAllPokemons().enqueue(object : Callback<List<Pokemon>> {
            override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                if (response.isSuccessful) {
                    pokemons = (response.body() as ArrayList<Pokemon>?)!!

                    /*pokemons.add(response.body()?.get(0)!!)
                    pokemons.add(response.body()?.get(1)!!)
                    pokemons.add(response.body()?.get(2)!!)*/
                    GlobalScope.launch {
                        //Dispatchers.Main
                        pokemonDao.insertAllPokemons(pokemons.map {
                            PokemonEntity(
                                //id = it.id.toInt(),
                                name = it.name,
                                category = it.category,
                                attack = it.attack,
                                defense = it.defense,
                                imageurl = it.imageurl
                            )
                        })

                        load(recyclerView)

                    }
                    Log.e(TAG, "onResponse: ${response.body()?.get(0)}")
                }
            }

            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                load(recyclerView)
                Log.e(TAG, "onFailure: ", t)
            }

        })
    }

    fun load(recyclerview: RecyclerView) {
        GlobalScope.launch {
            pokemonAdapter = PokemonAdapter(pokemonDao.getALLPokemons())

            runOnUiThread { recyclerview.adapter = pokemonAdapter }
        }
    }
}

