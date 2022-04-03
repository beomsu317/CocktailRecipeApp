package com.beomsu317.cocktailrecipeapp.data.remote

import com.beomsu317.cocktailrecipeapp.data.remote.dto.CategoriesDto
import com.beomsu317.cocktailrecipeapp.data.remote.dto.DrinkCategory
import retrofit2.http.GET

interface TheCocktailDbApi {

    @GET("/api/json/v1/1/list.php?c=list")
    suspend fun getListByCategories(): CategoriesDto
}