package com.reminmax.shoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }

}