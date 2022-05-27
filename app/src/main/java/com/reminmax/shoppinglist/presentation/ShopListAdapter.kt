package com.reminmax.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.reminmax.shoppinglist.R
import com.reminmax.shoppinglist.domain.ShopItem

class ShopListAdapter: androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(
    ShopItemDiffCallback())
{

    var shopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var shopItemClickListener : ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when(viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {

        val shopItem = getItem(position)

        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()

        viewHolder.itemView.setOnLongClickListener {
            shopItemLongClickListener?.invoke(shopItem)
            true
        }

        viewHolder.itemView.setOnClickListener {
            shopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {

        val item = getItem(position)

        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        const val MAX_POOL_SIZE = 15
    }
}