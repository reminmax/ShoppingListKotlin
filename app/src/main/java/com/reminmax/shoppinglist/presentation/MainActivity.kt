package com.reminmax.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.reminmax.shoppinglist.R
import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListadapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView();

        // Subscription to shopList changes
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListadapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListadapter = ShopListAdapter()
            rvShopList.adapter = shopListadapter

            rvShopList.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            rvShopList.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }

        setupLongClickListener()
        setupClickListener()
        setupSwipeToDeleteListener(rvShopList)
    }

    private fun setupSwipeToDeleteListener(rvShopList: RecyclerView) {
        // Swipe to delete listener
        var callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // в данном случае этот метод не нужен
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListadapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
        shopListadapter.shopItemClickListener = {
            Log.d("MainActivity", it.toString())
        }
    }

    private fun setupLongClickListener() {
        shopListadapter.shopItemLongClickListener = {
            viewModel.changeEnabledState(it)
        }
    }
}
