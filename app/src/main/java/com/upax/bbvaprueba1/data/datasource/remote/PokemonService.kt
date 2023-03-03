package com.upax.bbvaprueba1.data.datasource.remote

import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import com.upax.bbvaprueba1.enums.ApiType
import retrofit2.http.GET
import retrofit2.http.Headers

interface PokemonService {

    @Headers("Content-Type: application/json")
    @GET("/pokemon?limit=50")
    @Api(ApiType.URL_SECUNDARY)
    suspend fun getPokemon(): DataResponse
}
