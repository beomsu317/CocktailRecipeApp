package com.beomsu317.cocktailrecipeapp.domain.use_case


import com.beomsu317.cocktailrecipeapp.domain.model.CocktailInfo
import com.beomsu317.cocktailrecipeapp.domain.model.toCocktailInfoEntity
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import javax.inject.Inject

class InsertCocktailInfoUseCase @Inject constructor(
    private val repository: CocktailRepository
) {

    suspend operator fun invoke(cocktailInfo: CocktailInfo) {
        repository.insertCocktailInfo(cocktailInfo)
    }
}