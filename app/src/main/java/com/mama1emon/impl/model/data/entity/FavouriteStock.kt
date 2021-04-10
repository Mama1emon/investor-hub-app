package com.mama1emon.impl.model.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель любимых акций
 *
 * @author Andrey Khokhlov on 30.03.21
 */
@Entity(tableName = "favourite_stock")
data class FavouriteStock(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "ticker") val ticker: String
)