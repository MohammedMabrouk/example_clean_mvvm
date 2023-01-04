package com.mohamedmabrouk.goodsmart_task.ui.productList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmabrouk.goodsmart_task.R
import com.mohamedmabrouk.goodsmart_task.data.source.remote.RemoteProduct
import com.squareup.picasso.Picasso


class ProductItemsAdapter(
    private val productItemsList: List<RemoteProduct?>?,
    private val productItemClickListener: ProductItemClickListener
) : RecyclerView.Adapter<ProductItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_itemlayout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = productItemsList!![position]

        if(!productItem?.image.isNullOrEmpty())
        Picasso.get()
            .load(productItem?.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)

        holder.nameTextView.text = productItem?.title
        holder.priceTextView.text = "${productItem?.price.toString()}$"

        holder.cardView.setOnClickListener {
            productItemClickListener.onItemClick(productItem!!, it)
        }
    }

    override fun getItemCount(): Int {
        return productItemsList!!.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cv_container)
        val imageView: ImageView = itemView.findViewById(R.id.product_iv)
        val nameTextView: TextView = itemView.findViewById(R.id.title_tv)
        val priceTextView: TextView = itemView.findViewById(R.id.price_tv)
    }

    interface ProductItemClickListener {
        fun onItemClick(product: RemoteProduct, view: View)
    }
}