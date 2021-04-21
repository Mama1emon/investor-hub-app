package com.mama1emon.api.data

import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Интерфейс репозитория приложения
 *
 * @author Andrey Khokhlov on 26.03.21
 */
interface Repository {

    /**
     * Получить список акций
     */
    fun getStockSetContent(): Single<Set<Stock>>

    /**
     * Получить котировки акции. Запрос повторяется с определенным
     * интервалом
     *
     * @param ticker акции
     */
    fun getStockQuote(ticker: String): Observable<StockQuote>
}