package com.mama1emon.impl.model.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Тело ответа с котировками акции
 *
 * @param currentPrice текущая цена акции
 * @param openPrice цена акция с открытия дня
 *
 * @author Andrey Khokhlov on 27.03.21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class StockQuoteResponse @JsonCreator constructor(
    @param:JsonProperty("c") val currentPrice: String,
    @param:JsonProperty("o") val openPrice: String
) {
    //тикер акции для дальнейшей идентификации
    var ticker: String? = null
}
