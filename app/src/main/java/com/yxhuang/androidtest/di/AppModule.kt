package com.yxhuang.androidtest.di

import android.content.Context
import androidx.room.Room
import com.yxhuang.androidtest.data.local.ShoppingDao
import com.yxhuang.androidtest.data.local.ShoppingItemDatabase
import com.yxhuang.androidtest.data.remote.PixabayAPI
import com.yxhuang.androidtest.other.Constants
import com.yxhuang.androidtest.respositories.DefaultShoppingRepository
import com.yxhuang.androidtest.respositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by yxhuang
 * Date: 2022/5/3
 * Description:
 */
@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ShoppingItemDatabase::class.java,
        Constants.DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
            dao: ShoppingDao,
            api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun providesShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi():PixabayAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

}