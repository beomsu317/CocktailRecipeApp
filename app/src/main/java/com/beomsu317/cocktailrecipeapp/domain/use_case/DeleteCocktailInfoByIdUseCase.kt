package com.beomsu317.cocktailrecipeapp.domain.use_case

import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import javax.inject.Inject

class DeleteCocktailInfoByIdUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteCocktailInfoById(id)
    }
}