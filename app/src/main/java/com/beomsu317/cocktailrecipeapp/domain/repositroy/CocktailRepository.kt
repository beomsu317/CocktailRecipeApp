package com.beomsu317.cocktailrecipeapp.domain.repositroy

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beomsu317.cocktailrecipeapp.data.local.entity.CocktailInfoEntity
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailInfosDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailsDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.IngredientsDto
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {

    suspend fun getCategories(): CategoriesDto

    suspend fun getCocktails(category: String): CocktailsDto

    suspend fun getCocktailInfos(name: String): CocktailInfosDto

    suspend fun getIngredients(name: String): IngredientsDto

    suspend fun insertCocktailInfo(cocktailInfoEntity: CocktailInfoEntity)

    suspend fun deleteCocktailInfoById(id: Int)

    fun getCocktailInfoIds(): Flow<List<Int>>

    fun getMyCocktailinfo(): Flow<List<CocktailInfoEntity>>
}