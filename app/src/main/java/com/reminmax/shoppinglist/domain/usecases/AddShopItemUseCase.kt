package com.reminmax.shoppinglist.domain.usecases

import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.domain.repository.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }

}