package com.mohamedmabrouk.goodsmart_task.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val _id: String?,
    val description: String?,
    val image: String?,
    val price: Int?,
    val title: String?,
): Parcelable