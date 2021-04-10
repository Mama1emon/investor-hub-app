package com.mama1emon.impl.model.data.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Ответ со списком тикеров акций
 *
 * @param stockList лист с информацией об акциях
 *
 * @author Andrey Khokhlov on 27.03.21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class StockSetResponse @JsonCreator constructor(
    @param:JsonProperty("tickers") val stockList: Set<StockBean>
)