package com.mama1emon.api.data.net

import com.squareup.okhttp.Response

/**
 * Интерфейс апи маппера приложения
 *
 * @author Andrey Khokhlov on 27.03.21
 */
interface ApiMapper {

    /**
     * Запрос для получения списка акций
     */
    fun requestStockListContent(): Response

    /**
     * Запрос для получения котировок акции
     *
     * @param ticker акции
     */
    fun requestStockQuote(ticker: String): Response
}