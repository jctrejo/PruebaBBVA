package com.upax.bbvaprueba1.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { "BBVA" }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Pokemon",
                        style = MaterialTheme.typography.h3
                    )
                }
            }
            items(listPokemon.size) { pokemon ->
                PlantCard(
                    listPokemon[pokemon].name,
                    listPokemon[pokemon].name,
                    listPokemon[pokemon].url
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlantCard(name: String, description: String, imagePokemon: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                model = imagePokemon,
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}
