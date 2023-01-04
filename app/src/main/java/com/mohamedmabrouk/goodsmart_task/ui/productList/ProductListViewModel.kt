package com.mohamedmabrouk.goodsmart_task.ui.productList

import androidx.lifecycle.*
import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.IProductsRepository
import com.mohamedmabrouk.goodsmart_task.data.source.remote.ProductsResponse
import com.mohamedmabrouk.goodsmart_task.domain.GetProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) :
    ViewModel() {

    private val _products = MutableLiveData<DataResult<ProductsResponse>>()
    val products: LiveData<DataResult<ProductsResponse>> get() = _products

    fun getProducts() {
        viewModelScope.launch {
            _products.value = DataResult.Loading()
            getProductsUseCase.execute().collect {
                _products.value = it
            }
        }
    }

    class Factory @Inject constructor(private val repository: IProductsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductListViewModel(GetProductsUseCase(repository)) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}