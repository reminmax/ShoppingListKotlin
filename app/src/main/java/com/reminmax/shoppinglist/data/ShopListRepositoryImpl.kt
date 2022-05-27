package com.reminmax.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.domain.repository.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ item1, item2 -> item1.id.compareTo(item2.id)})

    private var autoIncrementId = 0

    init {
        // Add demo data
        for (i in 0 until 100) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }

        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId was not found.")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        // return copy
        return shopListLiveData
    }

    private fun updateList() {
        // return copy
        shopListLiveData.value = shopList.toList()
    }
}