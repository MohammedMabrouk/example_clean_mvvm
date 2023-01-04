package com.mohamedmabrouk.goodsmart_task.data.source

import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.remote.ProductsResponse
import kotlinx.coroutines.flow.Flow

interface IProductsRepository {
    suspend fun getProducts(): Flow<DataResult<ProductsResponse>>
}