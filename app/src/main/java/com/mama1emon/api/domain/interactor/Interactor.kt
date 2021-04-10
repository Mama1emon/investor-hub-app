package com.mama1emon.api.domain.interactor

import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Интерфейс интерактора приложения
 *
 * @author Andrey Khokhlov on 26.03.21
 */
interface Interactor {

    /**
     * Получить список акций
     */
    fun getStockSetContent(): Single<Set<Stock>>

    /**
     * Получить котировки акции
     *
     * @param ticker акции
     */
    fun getStockQuote(ticker: String): Single<StockQuote>

    /**
     * Получить набор любимых акций
     */
    fun getFavouriteStockList(): Single<List<FavouriteStock>>

    /**
     * Сохранить любимые акции
     *
     * @param stocks любимые акции
     */
    fun saveFavouriteStocks(vararg stocks: FavouriteStock): Completable

    /**
     * Удалить любимую акцию
     *
     * @param ticker акции
     */
    fun deleteFavouriteStock(ticker: String): Completable
}