package com.beomsu317.cocktailrecipeapp.domain.repositroy

import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto

interface CocktailRepository {

    suspend fun getCategories(): CategoriesDto
}