package com.mama1emon.impl.model.domain

/**
 * Котировка акции
 * @param ticker название акции
 * @param currentPrice текущая цена
 * @param openPrice цена с открытия дня
 *
 * @author Andrey Khokhlov on 27.03.21
 */
data class StockQuote(
    val ticker: String,
    val currentPrice: Double,
    val openPrice: Double
)