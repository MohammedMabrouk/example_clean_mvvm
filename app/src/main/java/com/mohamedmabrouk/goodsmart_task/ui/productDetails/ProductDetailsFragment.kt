package com.mohamedmabrouk.goodsmart_task.ui.productDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamedmabrouk.goodsmart_task.R
import com.mohamedmabrouk.goodsmart_task.data.source.remote.RemoteProduct
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_details.view.*

class ProductDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        val product: RemoteProduct? = arguments?.get("product") as RemoteProduct

        if(!product?.image.isNullOrEmpty())
            Picasso.get()
                .load(product?.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(view.rootView.product_iv)

        view.rootView.title_tv.text = product?.title
        view.rootView.price_tv.text = "${product?.price}$"
        view.rootView.description_tv.text = product?.description
        return view
    }
}