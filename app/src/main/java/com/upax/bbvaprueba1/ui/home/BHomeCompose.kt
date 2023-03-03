package com.upax.bbvaprueba1.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.upax.bbvaprueba1.data.datasource.response.PokemonsResponse

@Composable
fun SetupCompose(listPokemon: List<PokemonsResponse>) {
    AllPokemons(listPokemon)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllPokemons(listPokemon: List<PokemonsResponse>) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(listPokemon.size) { pokemon ->
                PokemonCard(
                    listPokemon[pokemon].name,
                    pokemon
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonCard(name: String, pokemonNumber: Int) {
    val pokemonImageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonNumber.png"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .width(120.dp)
                .height(200.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 3.dp,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            GlideImage(
                model = pokemonImageUrl,
                contentDescription = "",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(8.dp)
            )
        }

        Card(
            modifier = Modifier
                .padding(start = 0.dp, end = 20.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = 3.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.padding(25.dp)) {
                    PokemonTextView(name, MaterialTheme.typography.h6, 0)
                    PokemonTextView(name, MaterialTheme.typography.body1, 5)
                    PokemonTextView(name, MaterialTheme.typography.body1, 10)
                }
            }
        }
    }
}

@Composable
fun PokemonTextView(text: String, style: TextStyle, top: Int) {
    Text(
        text = text,
        style = style,
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier.padding(top = top.dp)
    )
}

@Preview
@Composable
fun PreviewGreeting() {
    val pokemonImageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"

    val list = ArrayList<PokemonsResponse>()
    list.add(PokemonsResponse("pokemon 1", pokemonImageUrl))
    list.add(PokemonsResponse("pokemon 2", pokemonImageUrl))
    list.add(PokemonsResponse("pokemon 3", pokemonImageUrl))

    SetupCompose(list)
}
