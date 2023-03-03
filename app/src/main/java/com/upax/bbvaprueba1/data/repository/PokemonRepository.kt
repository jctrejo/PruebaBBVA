package com.upax.bbvaprueba1.data.repository

import com.upax.bbvaprueba1.common.PokemonResource
import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun fetchAffinity(): Flow<PokemonResource<DataResponse>>
}