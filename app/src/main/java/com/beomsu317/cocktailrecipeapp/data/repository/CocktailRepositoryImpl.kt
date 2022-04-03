package com.beomsu317.cocktailrecipeapp.data.repository

import com.beomsu317.cocktailrecipeapp.data.remote.TheCocktailDbApi
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.DrinkCategory
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository

class CocktailRepositoryImpl(
    private val api: TheCocktailDbApi
): CocktailRepository {

    override suspend fun getCategories(): CategoriesDto {
        return api.getListByCategories()
    }
}