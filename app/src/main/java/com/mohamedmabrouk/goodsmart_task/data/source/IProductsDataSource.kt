package com.mohamedmabrouk.goodsmart_task.data.source

import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.remote.ProductsResponse

interface IProductsDataSource {
    suspend fun getProductsList(): DataResult<ProductsResponse>
}