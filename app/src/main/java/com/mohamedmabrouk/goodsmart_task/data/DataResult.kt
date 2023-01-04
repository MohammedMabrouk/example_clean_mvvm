package com.mohamedmabrouk.goodsmart_task.data

sealed class DataResult<T>(
    val data: T? = null,
    val errorCode: Int? = null
) {
    class Success<T>(data: T) : DataResult<T>(data)
    class Loading<T>(data: T? = null) : DataResult<T>(data)
    class DataError<T>(errorCode: Int) : DataResult<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"
        }
    }
}