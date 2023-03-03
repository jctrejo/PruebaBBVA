package com.upax.bbvaprueba1.data.datasource.remote

import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import com.upax.bbvaprueba1.data.datasource.response.DetailDataResponse
import com.upax.bbvaprueba1.enums.ApiType
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokemonService {

    @GET("/api/v2/pokemon?limit=50")
    @Api(ApiType.URL_SECUNDARY)
    suspend fun getPokemon(): DataResponse

    @GET("/api/v2/pokemon/{id}")
    @Api(ApiType.URL_SECUNDARY)
    suspend fun getDetailPokemon(
        @Path("id") id: Int
    ): DetailDataResponse
}
