package com.upax.bbvaprueba1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upax.bbvaprueba1.common.PokemonResource
import com.upax.bbvaprueba1.common.PokemonUiState
import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import com.upax.bbvaprueba1.data.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BHomeViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableLiveData<PokemonUiState<DataResponse>>()
    val uiState: LiveData<PokemonUiState<DataResponse>> get() = _uiState

    fun getPokemon() = viewModelScope.launch {
        repository.fetchAffinity().collect { result ->
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
