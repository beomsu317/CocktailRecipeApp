package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.data.remote.dto.toCocktailInfos
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCocktailInfosByNameUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    operator fun invoke(name: String): Flow<Resource<List<CocktailInfo>>> = flow {
        try {
            emit(Resource.Loading<List<CocktailInfo>>())
            val cocktailInfos = repository.getCocktailInfos(name).toCocktailInfos()
            if (cocktailInfos.isEmpty()) {
                emit(Resource.Error<List<CocktailInfo>>("Nothing exists"))
            } else {
                emit(Resource.Success(cocktailInfos))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<CocktailInfo>>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<List<CocktailInfo>>(e.localizedMessage))
        }
    }
}