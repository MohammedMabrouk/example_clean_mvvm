package com.mohamedmabrouk.goodsmart_task.data.source.remote

import android.os.Parcelable
import com.mohamedmabrouk.goodsmart_task.data.Product
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteProduct(
    val _id: String?,
    val category: Category?,
    val createdAt: String?,
    val createdBy: CreatedBy?,
    val description: String?,
    val image: String?,
    val price: Int?,
    val slug: String?,
    val title: String?,
    val updatedAt: String?
): Parcelable

fun List<RemoteProduct>.asDomainModel(): List<Product> {
    return map {
        Product(
            _id = it._id,
            description = it.description,
            image = it.image,
            price = it.price,
            title = it.title
        )
    }
}