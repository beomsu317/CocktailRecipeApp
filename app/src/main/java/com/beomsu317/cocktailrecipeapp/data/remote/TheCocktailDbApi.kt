package com.beomsu317.cocktailrecipeapp.data.remote

import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.CocktailsDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.DrinkCategory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheCocktailDbApi {

    @GET("api/json/v1/1/list.php?c=list")
    suspend fun getListByCategories(): CategoriesDto

    @GET("api/json/v1/1/filter.php")
    suspend fun getCocktails(@Query("c") category: String): CocktailsDto
}