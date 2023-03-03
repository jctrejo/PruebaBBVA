package com.upax.bbvaprueba1.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonDao {
    @Insert
    fun insert(pokemon: PokemonEntity)

    @Update
    fun update(vararg pokemon: PokemonEntity)

    @Delete
    fun delete(vararg pokemon: PokemonEntity)

    @Query("SELECT * FROM " + PokemonEntity.TABLE_NAME + " ORDER BY name, url")
    fun getOrderedAgenda(): LiveData<List<PokemonEntity>>
}