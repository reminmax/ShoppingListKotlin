package com.reminmax.shoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reminmax.shoppinglist.data.dao.ShopListDao

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppdDatabase: RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object {

        private var INSTANCE: AppdDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "shop_items.db"

        fun getInstance(application: Application): AppdDatabase {

            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
            }
            var db = Room.databaseBuilder(
                application,
                AppdDatabase::class.java,
                DB_NAME
            ).build()

            INSTANCE = db

            return db
        }

    }

}