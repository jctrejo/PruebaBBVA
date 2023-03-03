package com.upax.bbvaprueba1.data.local.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class PokemonRepository(application: Application) {
    private val pokemonDao: PokemonDao? = PokemonDatabase.getInstance(application)?.pokemonDao()

    fun insert(pokemon: PokemonEntity) {
        if (pokemonDao != null) InsertAsyncTask(pokemonDao).execute(pokemon)
    }

    fun getPokemons(): LiveData<List<PokemonEntity>> {
        return pokemonDao?.getOrderedAgenda() ?: MutableLiveData<List<PokemonEntity>>()
    }

    private class InsertAsyncTask(private val pokemonDao: PokemonDao) :
        AsyncTask<PokemonEntity, Void, Void>() {
        override fun doInBackground(vararg pokemons: PokemonEntity?): Void? {
            for (pokemon in pokemons) {
                if (pokemon != null) pokemonDao.insert(pokemon)
            }
            return null
        }
    }
}