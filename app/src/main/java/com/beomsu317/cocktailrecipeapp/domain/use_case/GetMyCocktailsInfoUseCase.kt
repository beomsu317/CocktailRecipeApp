package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.common.Resource
import com.beomsu317.cocktailrecipeapp.data.local.entity.toCocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

class GetMyCocktailsInfoUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    operator fun invoke(): Flow<List<CocktailInfo>> {
        return repository.getMyCocktailsInfoEntities().map {
            it.map { it.toCocktailInfo() }
        }
    }
}