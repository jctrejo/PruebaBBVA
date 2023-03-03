package com.upax.bbvaprueba1.data.datasource.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("count")
    var count: String,
    @SerializedName("next")
    var next: String,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    var results: ArrayList<PokemonsResponse>
)

data class PokemonsResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)