package com.beomsu317.cocktailrecipeapp.domain.use_case

import android.util.Log
import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.data.remote.dto.toIngredient
import com.beomsu317.cocktailrecipeapp.domain.model.Ingredient
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetIngredientByNameUseCase @Inject constructor(
    private val repository: CocktailRepository
) {

    operator fun invoke(name: String): Flow<Resource<Ingredient>> = flow {
        try {
            emit(Resource.Loading())
            val ingredientsDto = repository.getIngredients(name)
            val ingredients = ingredientsDto.ingredients?.let {
                it.map {
                    it.toIngredient()
                }
            } ?: emptyList()
            if (ingredients.isEmpty()) {
                emit(Resource.Error<Ingredient>("Nothing exists"))
            } else {
                emit(Resource.Success(ingredients.first()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<Ingredient>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<Ingredient>(e.localizedMessage))
        }
    }
}