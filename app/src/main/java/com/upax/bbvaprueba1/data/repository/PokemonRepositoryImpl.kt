package com.upax.bbvaprueba1.data.repository

import com.upax.bbvaprueba1.common.PokemonResource
import com.upax.bbvaprueba1.data.datasource.remote.PokemonService
import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import com.upax.bbvaprueba1.data.datasource.response.DetailDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val  remote: PokemonService
) : PokemonRepository {

    override suspend fun fetchPokemon(): Flow<PokemonResource<DataResponse>> =
        flow {
            try {
                emit(PokemonResource.Loading)
                val response = remote.getPokemon()
                emit(PokemonResource.Success(response))
            } catch (exception: HttpException) {
                emit(PokemonResource.Error(exception.message() ?: "no internet connection."))
            } catch (exception: IOException) {
                emit(PokemonResource.Error(exception.toString()))
            }
        }

    override suspend fun fetchDetailPokemon(id: Int): Flow<PokemonResource<DetailDataResponse>> =
        flow {
            try {
                emit(PokemonResource.Loading)
                val response = remote.getDetailPokemon(id)
                emit(PokemonResource.Success(response))
            } catch (exception: HttpException) {
                emit(PokemonResource.Error(exception.message() ?: "no internet connection."))
            } catch (exception: IOException) {
                emit(PokemonResource.Error(exception.toString()))
            }
        }
}