package com.beomsu317.cocktailrecipeapp.data.remote.dto


import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailInfosDto(
    @SerialName("drinks")
    val drinks: List<DrinkCocktailInfo>?
)

fun CocktailInfosDto.toCocktailInfos(): List<CocktailInfo> {
    return drinks?.let{
        it.map { drink ->
            val ingredients = mutableListOf<Pair<String, String?>>()

            drink.strIngredient1?.let {
                ingredients.add(Pair(it, drink.strMeasure1))
            }
            drink.strIngredient2?.let {
                ingredients.add(Pair(it, drink.strMeasure2))
            }
            drink.strIngredient3?.let {
                ingredients.add(Pair(it, drink.strMeasure3))
            }
            drink.strIngredient4?.let {
                ingredients.add(Pair(it, drink.strMeasure4))
            }
            drink.strIngredient5?.let {
                ingredients.add(Pair(it, drink.strMeasure5))
            }
            drink.strIngredient6?.let {
                ingredients.add(Pair(it, drink.strMeasure6))
            }
            drink.strIngredient7?.let {
                ingredients.add(Pair(it, drink.strMeasure7))
            }
            drink.strIngredient8?.let {
                ingredients.add(Pair(it, drink.strMeasure8))
            }
            drink.strIngredient9?.let {
                ingredients.add(Pair(it, drink.strMeasure9))
            }
            drink.strIngredient10?.let {
                ingredients.add(Pair(it, drink.strMeasure10))
            }
            drink.strIngredient11?.let {
                ingredients.add(Pair(it, drink.strMeasure11))
            }
            drink.strIngredient12?.let {
                ingredients.add(Pair(it, drink.strMeasure12))
            }
            drink.strIngredient13?.let {
                ingredients.add(Pair(it, drink.strMeasure13))
            }
            drink.strIngredient14?.let {
                ingredients.add(Pair(it, drink.strMeasure14))
            }
            drink.strIngredient15?.let {
                ingredients.add(Pair(it, drink.strMeasure15))
            }

            CocktailInfo(
                dateModified = drink.dateModified,
                idDrink = drink.idDrink,
                strAlcoholic = drink.strAlcoholic,
                strCategory = drink.strCategory,
                strDrink = drink.strDrink,
                strGlass = drink.strGlass,
                strDrinkThumb = drink.strDrinkThumb,
                ingredients = ingredients,
                strInstructions = drink.strInstructions,
            )
        }
    } ?: emptyList()
}