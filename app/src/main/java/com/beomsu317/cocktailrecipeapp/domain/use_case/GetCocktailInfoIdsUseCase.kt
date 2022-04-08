package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailInfoIdsUseCase @Inject constructor(
    private val repository: CocktailRepository
) {

    operator fun invoke(): Flow<List<Int>> {
        return repository.getCocktailInfoIds()
    }
}