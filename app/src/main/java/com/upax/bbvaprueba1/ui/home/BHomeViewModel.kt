package com.upax.bbvaprueba1.ui.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upax.bbvaprueba1.common.PokemonResource
import com.upax.bbvaprueba1.common.PokemonUiState
import com.upax.bbvaprueba1.data.datasource.response.DataResponse
import com.upax.bbvaprueba1.data.datasource.response.PokemonsResponse
import com.upax.bbvaprueba1.data.local.room.PokemonEntity
import com.upax.bbvaprueba1.data.local.room.PokemonRepository
import com.upax.bbvaprueba1.data.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BHomeViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl,
    @ApplicationContext context: Context
) : ViewModel() {

    private val _uiState = MutableLiveData<PokemonUiState<DataResponse>>()
    val uiState: LiveData<PokemonUiState<DataResponse>> get() = _uiState

    private val repositoryLocal = PokemonRepository(context.applicationContext as Application)
    val pokemon = repositoryLocal.getPokemons()

    fun getPokemon() = viewModelScope.launch {
        repository.fetchAffinity().collect { result ->
            when (result) {
                is PokemonResource.Loading -> {
                    _uiState.value = PokemonUiState.Loading()
                }
                is PokemonResource.Success -> {
                    _uiState.value = PokemonUiState.Success(result.item)
                    setupSavePokemon(result.item.results)
                }
                is PokemonResource.Error -> {
                    _uiState.value = PokemonUiState.Error(result.throwable)
                }
            }
        }
    }

    private fun setupSavePokemon(pokemons: ArrayList<PokemonsResponse>) {
        pokemons.forEach {
            savePokemon(PokemonEntity(it.name, it.url))
        }
    }

    private fun savePokemon(pokemon: PokemonEntity) {
        repositoryLocal.insert(pokemon)
    }
}
