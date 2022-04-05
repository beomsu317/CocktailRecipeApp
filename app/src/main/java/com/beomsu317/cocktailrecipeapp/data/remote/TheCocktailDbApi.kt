package com.beomsu317.cocktailrecipeapp.data.remote

import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailInfosDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailsDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.IngredientsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCocktailDbApi {

    @GET("api/json/v1/1/list.php?c=list")
    suspend fun getListByCategories(): CategoriesDto

    @GET("api/json/v1/1/filter.php")
    suspend fun getCocktails(@Query("c") category: String): CocktailsDto

    @GET("/api/json/v1/1/search.php")
    suspend fun getCocktailInfos(@Query("s") name: String): CocktailInfosDto

    @GET("/api/json/v1/1/search.php")
    suspend fun getIngredients(@Query("i") ingredient: String): IngredientsDto
}