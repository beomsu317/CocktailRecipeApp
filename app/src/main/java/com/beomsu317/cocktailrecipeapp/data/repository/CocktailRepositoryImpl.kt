package com.beomsu317.cocktailrecipeapp.data.repository

import com.beomsu317.cocktailrecipeapp.data.local.dao.CocktailDao
import com.beomsu317.cocktailrecipeapp.data.local.entity.CocktailInfoEntity
import com.beomsu317.cocktailrecipeapp.data.remote.TheCocktailDbApi
import com.beomsu317.cocktailrecipeapp.data.remote.dto.*
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow

class CocktailRepositoryImpl(
    private val api: TheCocktailDbApi,
    private val dao: CocktailDao
): CocktailRepository {

    override suspend fun getCategories(): CategoriesDto {
        return api.getListByCategories()
    }

    override suspend fun getCocktails(category: String): CocktailsDto {
        return api.getCocktails(category)
    }

    override suspend fun getCocktailInfos(name: String): CocktailInfosDto {
        return api.getCocktailInfos(name)
    }

    override suspend fun getIngredients(name: String): IngredientsDto {
        return api.getIngredients(name)
    }

    override suspend fun insertCocktailInfo(cocktailInfoEntity: CocktailInfoEntity) {
        dao.insertCocktailInfo(cocktailInfoEntity)
    }

    override suspend fun deleteCocktailInfoById(id: Int) {
        dao.deleteCocktailInfoById(id)
    }

    override fun getCocktailInfoIds(): Flow<List<Int>> {
        return dao.getCocktailInfoIds()
    }

    override fun getMyCocktailinfo(): Flow<List<CocktailInfoEntity>> {
        return dao.getMyCocktailinfo()
    }
}