package com.beomsu317.cocktailrecipeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.beomsu317.cocktailrecipeapp.data.local.dao.CocktailDao
import com.beomsu317.cocktailrecipeapp.data.local.entity.CocktailInfoEntity

@Database(
    entities = [CocktailInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CocktailDatabase: RoomDatabase() {
    abstract val dao: CocktailDao
}