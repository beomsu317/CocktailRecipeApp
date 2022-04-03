package com.beomsu317.cocktailrecipeapp.di

import com.beomsu317.cocktailrecipeapp.common.Constants.BASE_URL
import com.beomsu317.cocktailrecipeapp.data.remote.TheCocktailDbApi
import com.beomsu317.cocktailrecipeapp.data.repository.CocktailRepositoryImpl
import com.beomsu317.cocktailrecipeapp.domain.repositroy.CocktailRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideCocktailRepository(theCocktailDbApi: TheCocktailDbApi): CocktailRepository {
        return CocktailRepositoryImpl(theCocktailDbApi)
    }
}