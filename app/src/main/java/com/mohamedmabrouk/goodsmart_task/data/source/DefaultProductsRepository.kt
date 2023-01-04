package com.mohamedmabrouk.goodsmart_task.data.source

import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.remote.ProductsResponse
import com.mohamedmabrouk.goodsmart_task.data.source.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultProductsRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    IProductsRepository {

    override suspend fun getProducts(): Flow<DataResult<ProductsResponse>> {
        return flow {
            emit(remoteDataSource.getProductsList())
        }.flowOn(Dispatchers.IO)
    }


}