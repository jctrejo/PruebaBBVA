package com.upax.bbvaprueba1.data.repository

import com.upax.bbvaprueba1.common.PokemonResource
import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import com.upax.bbvaprueba1.data.datasource.response.DetailDataResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun fetchPokemon(): Flow<PokemonResource<DataResponse>>

    suspend fun fetchDetailPokemon(id: Int): Flow<PokemonResource<DetailDataResponse>>
}