package com.mama1emon.impl.data.net

import android.content.res.Resources
import com.mama1emon.R
import com.mama1emon.api.data.net.ApiMapper
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response

/**
 * Реализация интерфейса апи маппера приложения
 *
 * @param resources ресурсы приложения
 *
 * @author Andrey Khokhlov on 27.03.21
 */
class ApiMapperImpl(resources: Resources) : ApiMapper {
    private val httpClient = OkHttpClient()

    //токены хранятся в local.properties
    private val finnhubToken = resources.getString(R.string.finnhub_token)
    private val polygonToken = resources.getString(R.string.polygon_token)

    override fun requestStockListContent(): Response {
        val request = Request.Builder()
            //YNDX, AAPL, GOOGL, AMZN, MSFT, TSLA, MA, ATVI, Facebook, Caterpillar
            .url("https://api.polygon.io/v2/reference/tickers?" +
                    "market=STOCKS&search=YNDX, AAPL, GOOGL, AMZN, MSFT, TSLA, MA, ATVI, Facebook, Caterpillar&" +
                    "perpage=50&page=1&active=true&apiKey=${polygonToken}")
            .build()
        return httpClient.newCall(request).execute()
    }

    override fun requestStockQuote(ticker: String): Response {
        val request = Request.Builder()
            .url("https://finnhub.io/api/v1/quote?symbol=$ticker&token=${finnhubToken}")
            .build()
        return httpClient.newCall(request).execute()
    }
}