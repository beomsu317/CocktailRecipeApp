package com.beomsu317.cocktailrecipeapp.domain.repositroy

import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailsDto

interface CocktailRepository {

    suspend fun getCategories(): CategoriesDto

    suspend fun getCocktails(category: String): CocktailsDto
}