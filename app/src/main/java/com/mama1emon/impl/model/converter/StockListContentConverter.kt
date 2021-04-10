package com.mama1emon.impl.model.converter

import com.mama1emon.api.model.converter.Converter
import com.mama1emon.impl.model.data.response.StockSetResponse
import com.mama1emon.impl.model.domain.Stock

/**
 * Конвертер из ответа со списком акций в список акций
 *
 * @author Andrey Khokhlov on 27.03.21
 */
class StockListContentConverter : Converter<StockSetResponse, Set<Stock>> {

    override fun convert(from: StockSetResponse) =
        from.stockList
            .map { stockBean ->
                Stock(
                    iconUrl = stockBean.ticker,
                    ticker = stockBean.ticker,
                    name = removeStockPostfix(stockBean.name)
                )
            }.toSet()

    /**
     * Удалить лишние строки
     *
     * @param string исходная строка
     */
    private fun removeStockPostfix(string: String): String {
        var temp = string
        temp = temp.replace("Class A", "")
        temp = temp.replace("Ordinary Shares", "")
        temp = temp.replace("Common Stock", "")
        temp = temp.replace("Ltd American Depositary Receipts - Unsponsored", "")
        return temp.trim()
    }
}