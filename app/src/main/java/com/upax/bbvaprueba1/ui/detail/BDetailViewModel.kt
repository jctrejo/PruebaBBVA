package com.upax.bbvaprueba1.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upax.bbvaprueba1.common.PokemonResource
import com.upax.bbvaprueba1.common.PokemonUiState
import com.upax.bbvaprueba1.data.datasource.response.DetailDataResponse
import com.upax.bbvaprueba1.data.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BDetailViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableLiveData<PokemonUiState<DetailDataResponse>>()
    val uiState: LiveData<PokemonUiState<DetailDataResponse>> get() = _uiState

    fun getDetailPokemon(id: Int) = viewModelScope.launch {
        repository.fetchDetailPokemon(id).collect { result ->
            when (result) {
                is PokemonResource.Loading -> {
                    _uiState.value = PokemonUiState.Loading()
                }
                is PokemonResource.Success -> {
                    _uiState.value = PokemonUiState.Success(result.item)
                }
                is PokemonResource.Error -> {
                    _uiState.value = PokemonUiState.Error(result.throwable)
                }
            }
        }
    }
}