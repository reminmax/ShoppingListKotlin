package com.reminmax.shoppinglist.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reminmax.shoppinglist.data.ShopListRepositoryImpl
import com.reminmax.shoppinglist.domain.ShopItem
import com.reminmax.shoppinglist.domain.usecases.*

class MainViewModel: ViewModel() {

    // TODO: inject repository using DI (to break the dependency of the ViewModel on Domain layer)
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}