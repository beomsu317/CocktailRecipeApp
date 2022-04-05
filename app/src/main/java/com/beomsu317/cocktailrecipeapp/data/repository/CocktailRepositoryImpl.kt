package com.beomsu317.cocktailrecipeapp.data.repository

import com.beomsu317.cocktailrecipeapp.data.remote.TheCocktailDbApi
import com.beomsu317.cocktailrecipeapp.data.remote.dto.*
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository

class CocktailRepositoryImpl(
    private val api: TheCocktailDbApi
): CocktailRepository {

    override suspend fun getCategories(): CategoriesDto {
        return api.getListByCategories()
    }

    override suspend fun getCocktails(category: String): CocktailsDto {
        return api.getCocktails(category)
    }

    override suspend fun getCocktailInfos(name: String): CocktailInfosDto {
        return api.getCocktailInfos(name = name)
    }

    override suspend fun getIngredients(name: String): IngredientsDto {
        return api.getIngredients(name)
    }
}