package com.mohamedmabrouk.goodsmart_task.domain

import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.IProductsRepository
import com.mohamedmabrouk.goodsmart_task.data.source.remote.ProductsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: IProductsRepository) {
    suspend fun execute(): Flow<DataResult<ProductsResponse>> {
        return repository.getProducts()
    }
}