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
            val ingredients = repository.getIngredients(name)
            ingredients.ingredients.map {
                emit(Resource.Success(it.toIngredient()))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<Ingredient>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<Ingredient>(e.localizedMessage))
        }
    }
}