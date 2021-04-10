package com.mama1emon.impl.model.domain

/**
 * Модель данных - акция
 * @param iconUrl ссылка на иконку
 * @param ticker название акции
 * @param name название компании
 *
 * @author Andrey Khokhlov on 24.03.21
 */
data class Stock(
    val iconUrl: String,
    val ticker: String,
    val name: String
){
    var quote: StockQuote? = null
    var isFavourite: Boolean? = null
}
