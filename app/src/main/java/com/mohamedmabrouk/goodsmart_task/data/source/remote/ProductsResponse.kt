package com.mohamedmabrouk.goodsmart_task.data.source.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsResponse(
    val data: List<RemoteProduct?>?,
    val message: String?,
    val status: Int?
): Parcelable

@Parcelize
data class Category(
    val _id: String?,
    val name: String?,
    val slug: String?
): Parcelable

@Parcelize
data class CreatedBy(
    val _id: String?,
    val name: String?,
    val role: String?
): Parcelable