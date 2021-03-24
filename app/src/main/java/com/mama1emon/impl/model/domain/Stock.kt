package com.mama1emon.impl.model.domain

/**
 * Модель данных - акция
 * @param iconUrl ссылка на иконку
 * @param stockName название акции
 * @param companyName название компании
 * @param currentPrice текущая стоимость
 * @param dayDelta дневная разность
 *
 * @author Andrey Khokhlov on 24.03.21
 */
data class Stock(
    val iconUrl: String,
    val stockName: String,
    val companyName: String,
    val currentPrice: String,
    val dayDelta: String
)
