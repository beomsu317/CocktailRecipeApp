package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.data.remote.dto.toCocktails
import com.beomsu317.cocktailrecipeapp.domain.model.Cocktail
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCocktailsByCategoryUseCase @Inject constructor(
    private val repository: CocktailRepository
) {

    operator fun invoke(category: String): Flow<Resource<List<Cocktail>>> = flow {
        try {
            emit(Resource.Loading())
            val cocktails = repository.getCocktails(category)
            emit(Resource.Success(cocktails.toCocktails()))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Cocktail>>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<List<Cocktail>>(e.localizedMessage))
        }
    }
}