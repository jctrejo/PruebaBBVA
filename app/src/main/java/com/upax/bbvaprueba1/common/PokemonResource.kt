package com.upax.bbvaprueba1.common

sealed class PokemonResource<out T> {
    object Loading : PokemonResource<Nothing>()
    data class Success<T>(val item: T) : PokemonResource<T>()
    data class Error(val throwable: String) : PokemonResource<Nothing>()
}
