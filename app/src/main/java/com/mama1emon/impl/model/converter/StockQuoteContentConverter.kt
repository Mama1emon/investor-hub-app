package com.mama1emon.impl.model.converter

import com.mama1emon.api.model.converter.Converter
import com.mama1emon.impl.model.data.StockQuoteResponse
import com.mama1emon.impl.model.domain.StockQuote

/**
 * Конвертер из [StockQuoteResponse] в [StockQuote]
 *
 * @author Andrey Khokhlov on 27.03.21
 */
class StockQuoteContentConverter : Converter<StockQuoteResponse, StockQuote> {
    override fun convert(from: StockQuoteResponse): StockQuote {
        return StockQuote(
            from.ticker.orEmpty(),
            from.currentPrice.toDouble(),
            from.openPrice.toDouble()
        )
    }
}