package com.mama1emon.api.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mama1emon.api.data.db.FavouriteStockDAO
import com.mama1emon.impl.model.data.entity.FavouriteStock

/**
 * База данных приложения
 *
 * @author Andrey Khokhlov on 30.03.21
 */
@Database(entities = [FavouriteStock::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteStockDao(): FavouriteStockDAO
}