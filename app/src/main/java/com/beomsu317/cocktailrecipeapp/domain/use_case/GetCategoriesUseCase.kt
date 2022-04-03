package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.data.remote.dto.toCategories
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repository.getCategories()
            emit(Resource.Success(categories.toCategories()))
        } catch (e: HttpException) {
            emit(Resource.Error<List<String>>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<List<String>>(e.localizedMessage))
        }
    }
}