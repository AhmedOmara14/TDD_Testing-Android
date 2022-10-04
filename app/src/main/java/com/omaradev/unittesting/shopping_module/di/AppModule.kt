package com.omaradev.unittesting.shopping_module.di

import android.content.Context
import androidx.room.Room
import com.omaradev.unittesting.shopping_module.common.Constants.BASE_URL
import com.omaradev.unittesting.shopping_module.common.Constants.DATABASE_NAME
import com.omaradev.unittesting.shopping_module.data.local.ShoppingItemDatabase
import com.omaradev.unittesting.shopping_module.data.remote.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()


    @Singleton
    @Provides
    fun provideDao(database: ShoppingItemDatabase) =database.shoppingDao()

    @Provides
    @Singleton
    fun provideRetrofit() : Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}