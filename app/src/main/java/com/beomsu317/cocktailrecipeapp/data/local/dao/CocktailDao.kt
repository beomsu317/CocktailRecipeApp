package com.beomsu317.cocktailrecipeapp.data.local.dao

import androidx.room.*
import com.beomsu317.cocktailrecipeapp.data.local.entity.CocktailInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktailInfo(cocktailInfoEntity: CocktailInfoEntity)

    @Query("DELETE FROM cocktailinfoentity WHERE idDrink=:id")
    suspend fun deleteCocktailInfoById(id: Int)

    @Query("SELECT * FROM cocktailinfoentity")
    fun getMyCocktailinfo(): Flow<List<CocktailInfoEntity>>

    @Query("SELECT cocktailinfoentity.idDrink FROM cocktailinfoentity")
    fun getCocktailInfoIds(): Flow<List<Int>>
}