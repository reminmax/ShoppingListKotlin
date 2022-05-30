package com.reminmax.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.reminmax.shoppinglist.domain.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun deleteShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun getShopItem(shopItemId: Int): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>

}