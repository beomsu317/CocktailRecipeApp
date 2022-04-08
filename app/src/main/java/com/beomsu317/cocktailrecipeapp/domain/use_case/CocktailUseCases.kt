package com.beomsu317.cocktailrecipeapp.domain.use_case

data class CocktailUseCases(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getCocktailInfoIdsUseCase: GetCocktailInfoIdsUseCase,
    val getCocktailInfosByNameUseCase: GetCocktailInfosByNameUseCase,
    val getCocktailsByCategoryUseCase: GetCocktailsByCategoryUseCase,
    val getIngredientByNameUseCase: GetIngredientByNameUseCase,
    val insertCocktailInfoUseCase: InsertCocktailInfoUseCase,
    val deleteCocktailInfoByIdUseCase: DeleteCocktailInfoByIdUseCase
)
