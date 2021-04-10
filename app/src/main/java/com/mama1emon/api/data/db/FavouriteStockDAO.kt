package com.mama1emon.api.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mama1emon.impl.model.data.entity.FavouriteStock
import io.reactivex.Completable
import io.reactivex.Single

/**
 * DAO для таблицы любимых акций
 *
 * @author Andrey Khokhlov on 30.03.21
 */
@Dao
interface FavouriteStockDAO {

    /**
     * Получить список всех любимых акций
     */
    @Query("SELECT * FROM favourite_stock")
    fun getAll(): Single<List<FavouriteStock>>

    /**
     * Сохранить массив любимых акций
     *
     * @param stocks массив акций
     */
    @Insert
    fun insertAll(vararg stocks: FavouriteStock): Completable

    /**
     * Удалить дюбимую акцию по тикеру
     *
     * @param ticker акции
     */
    @Query("DELETE FROM favourite_stock WHERE ticker LIKE :ticker")
    fun deleteByTicker(ticker: String): Completable
}