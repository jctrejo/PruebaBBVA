package com.upax.bbvaprueba1.common

sealed class PokemonUiState<T> {
    class Loading<T> : PokemonUiState<T>()
    data class Success<T>(val data: T) : PokemonUiState<T>()
    data class Error<T>(val error : String) : PokemonUiState<T>()
}
