package com.reminmax.shoppinglist.data.mappers

import com.reminmax.shoppinglist.data.ShopItemDbModel
import com.reminmax.shoppinglist.domain.ShopItem

// класс Mapper
class ShopListMapper {

    // преобразование сущности Domain слоя в сущность Data слоя (модель базы данных)
    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    // обратное преобразование (преобразование сущности Data слоя к сущности Domain слоя)
    fun mapDbModelToEntity(dbModel: ShopItemDbModel) : ShopItem = ShopItem(
        id = dbModel.id,
        name = dbModel.name,
        count = dbModel.count,
        enabled = dbModel.enabled
    )

    // преобразование списка
    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> = list.map {
        mapDbModelToEntity(it)
    }

}