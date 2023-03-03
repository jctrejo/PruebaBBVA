package com.upax.bbvaprueba1.data.datasource.response

import com.google.gson.annotations.SerializedName

data class DetailDataResponse(
    @SerializedName("forms")
    var forms: ArrayList<FormResponse>,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    var results: ArrayList<PokemonsResponse>
)

data class FormResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)

data class AbilitiesResponse(
    @SerializedName("ability")
    var ability: ArrayList<FormResponse>
)
