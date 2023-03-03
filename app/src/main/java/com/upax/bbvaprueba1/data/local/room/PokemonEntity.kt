package com.upax.bbvaprueba1.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = PokemonEntity.TABLE_NAME)
data class PokemonEntity(
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "url") @NotNull val url: String
) {
    companion object {
        const val TABLE_NAME = "pokemon"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pokemon_id")
    var pokemonId: Int = 0
}