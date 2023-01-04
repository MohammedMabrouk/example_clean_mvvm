package com.mohamedmabrouk.goodsmart_task.ui.productList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamedmabrouk.goodsmart_task.R
import com.mohamedmabrouk.goodsmart_task.data.DataResult
import com.mohamedmabrouk.goodsmart_task.data.source.DefaultProductsRepository
import com.mohamedmabrouk.goodsmart_task.data.source.remote.RemoteDataSource
import com.mohamedmabrouk.goodsmart_task.data.source.remote.RemoteProduct
import com.mohamedmabrouk.goodsmart_task.databinding.FragmentProductListBinding
import com.mohamedmabrouk.goodsmart_task.domain.GetProductsUseCase
import com.mohamedmabrouk.goodsmart_task.utils.Network

class ProductListFragment : Fragment(), ProductItemsAdapter.ProductItemClickListener {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(activity)
        binding.productsRecycler.layoutManager = layoutManager
        binding.productsRecycler.setHasFixedSize(true)

        viewModel = ProductListViewModel(
            GetProductsUseCase(DefaultProductsRepository(RemoteDataSource(Network((activity as Context)))))
        )

        viewModel.products.observe(viewLifecycleOwner) { it ->
            when (it) {
                is DataResult.DataError -> showError(it.errorCode?.toString())
                is DataResult.Loading -> showLoading()
                is DataResult.Success -> showList(it.data?.data)
            }
        }

        viewModel.getProducts()
        return binding.root
    }

    private fun showLoading() {
        binding.statusLoadingWheel.visibility = View.VISIBLE
        binding.productsRecycler.visibility = View.GONE
    }

    private fun showList(productList: List<RemoteProduct?>?) {
        binding.statusLoadingWheel.visibility = View.GONE
        binding.productsRecycler.visibility = View.VISIBLE

        binding.productsRecycler.layoutManager = LinearLayoutManager(activity)
        val adapter = ProductItemsAdapter(productList, this)
        binding.productsRecycler.adapter = adapter
    }

    private fun showError(errorCode: String?) {
        binding.statusLoadingWheel.visibility = View.GONE
        binding.productsRecycler.visibility = View.GONE
        Toast.makeText(activity, "Error: $errorCode", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(product: RemoteProduct, view: View) {
        val bundle = bundleOf("product" to product)
        Navigation.findNavController(view)
            .navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle)
    }

}