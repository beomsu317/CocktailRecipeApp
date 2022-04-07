package com.beomsu317.cocktailrecipeapp.data.remote.dto


import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailsDto(
    @SerialName("drinks")
    val drinks: List<DrinkCocktail>?
)

fun CocktailsDto.toCocktails(): List<Cocktail> {
    return drinks?.let {
        it.map {
            Cocktail(
                strDrink = it.strDrink,
                strDrinkThumb = it.strDrinkThumb,
                idDrink = it.idDrink.toInt()
            )
        }
    } ?: emptyList()
}