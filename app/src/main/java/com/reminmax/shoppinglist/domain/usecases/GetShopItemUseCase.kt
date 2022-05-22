package com.reminmax.shoppinglist.domain.usecases

import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.domain.repository.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItem(shopItemId)
    }

}