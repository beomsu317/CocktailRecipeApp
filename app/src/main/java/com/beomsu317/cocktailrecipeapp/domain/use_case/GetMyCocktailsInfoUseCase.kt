package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMyCocktailsInfoUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    operator fun invoke(): Flow<List<CocktailInfo>> {
        return repository.getMyCocktailsInfo()
    }
}