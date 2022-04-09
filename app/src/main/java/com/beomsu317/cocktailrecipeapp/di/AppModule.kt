package com.beomsu317.cocktailrecipeapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beomsu317.cocktailrecipeapp.common.Constants.BASE_URL
import com.beomsu317.cocktailrecipeapp.data.local.CocktailDatabase
import com.beomsu317.cocktailrecipeapp.data.local.dao.CocktailDao
import com.beomsu317.cocktailrecipeapp.data.remote.TheCocktailDbApi
import com.beomsu317.cocktailrecipeapp.data.repository.CocktailRepositoryImpl
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import com.beomsu317.cocktailrecipeapp.domain.use_case.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheCocktailDbApi(): TheCocktailDbApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
            .create(TheCocktailDbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailDatabase(@ApplicationContext context: Context): CocktailDatabase {
        return Room.databaseBuilder(
            context,
            CocktailDatabase::class.java,
            "cocktail_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCocktailRepository(
        theCocktailDbApi: TheCocktailDbApi,
        db: CocktailDatabase
    ): CocktailRepository {
        return CocktailRepositoryImpl(theCocktailDbApi, db.dao)
    }

    @Provides
    @Singleton
    fun provideCocktailUseCases(repository: CocktailRepository): CocktailUseCases {
        return CocktailUseCases(
            getCategoriesUseCase = GetCategoriesUseCase(repository),
            getCocktailInfoIdsUseCase = GetCocktailInfoIdsUseCase(repository),
            getCocktailInfosByNameUseCase = GetCocktailInfosByNameUseCase(repository),
            getCocktailsByCategoryUseCase = GetCocktailsByCategoryUseCase(repository),
            getIngredientByNameUseCase = GetIngredientByNameUseCase(repository),
            insertCocktailInfoUseCase = InsertCocktailInfoUseCase(repository),
            deleteCocktailInfoByIdUseCase = DeleteCocktailInfoByIdUseCase(repository),
            getMyCocktailsInfoUseCase = GetMyCocktailsInfoUseCase(repository)
        )
    }
}
