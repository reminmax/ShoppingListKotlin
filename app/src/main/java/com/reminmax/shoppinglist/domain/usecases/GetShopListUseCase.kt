package com.reminmax.shoppinglist.domain.usecases

import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.domain.repository.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList() :List<ShopItem> {
        return shopListRepository.getShopList()
    }

}