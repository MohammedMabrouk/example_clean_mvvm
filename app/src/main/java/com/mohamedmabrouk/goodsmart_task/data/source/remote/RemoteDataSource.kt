package com.mohamedmabrouk.goodsmart_task.data.source.remote

import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.IProductsDataSource
import com.mohamedmabrouk.goodsmart_task.utils.Constants.BASE_URL
import com.mohamedmabrouk.goodsmart_task.utils.Constants.NETWORK_ERROR
import com.mohamedmabrouk.goodsmart_task.utils.Constants.NO_INTERNET_CONNECTION
import com.mohamedmabrouk.goodsmart_task.utils.NetworkConnectivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val networkConnectivity: NetworkConnectivity) :
    IProductsDataSource {
    interface ProductService {
        @GET("products")
        suspend fun getProducts(): ProductsResponse
    }

    object ProductApi {
        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService: ProductService by lazy { retrofit.create(ProductService::class.java) }
    }

    override suspend fun getProductsList(): DataResult<ProductsResponse> {
        val productsService = ProductApi.retrofitService
        return when (val response = processCall(productsService::getProducts)) {
            is List<*> -> {
                DataResult.Success(
                    data = ProductsResponse(
                        response as ArrayList<RemoteProduct>,
                        message = "success",
                        200
                    )
                )
            }
            else -> {
                DataResult.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> ProductsResponse): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.status.toString()
            if (response.status == 200) {
                response.data
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}