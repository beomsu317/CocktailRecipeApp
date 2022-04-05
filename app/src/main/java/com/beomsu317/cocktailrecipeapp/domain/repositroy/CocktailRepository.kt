package com.beomsu317.cocktailrecipeapp.domain.repositroy

import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailInfosDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailsDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.IngredientsDto

interface CocktailRepository {

    suspend fun getCategories(): CategoriesDto

    suspend fun getCocktails(category: String): CocktailsDto

    suspend fun getCocktailInfos(name: String): CocktailInfosDto

    suspend fun getIngredients(name: String): IngredientsDto
}