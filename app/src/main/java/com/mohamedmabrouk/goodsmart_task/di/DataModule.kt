package com.mohamedmabrouk.goodsmart_task.di

import com.mohamedmabrouk.goodsmart_task.data.source.DefaultProductsRepository
import com.mohamedmabrouk.goodsmart_task.data.source.IProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(defaultProductsRepository: DefaultProductsRepository): IProductsDataSource
}
