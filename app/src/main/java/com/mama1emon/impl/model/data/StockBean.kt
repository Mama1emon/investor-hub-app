package com.mama1emon.impl.model.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Респонз с информацией об акции
 *
 * @param ticker тикер акции
 * @param name название акции
 *
 * @author Andrey Khokhlov on 27.03.21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class StockBean @JsonCreator constructor(
    @param:JsonProperty("ticker") val ticker: String,
    @param:JsonProperty("name") val name: String
)