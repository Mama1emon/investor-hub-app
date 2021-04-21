package com.mama1emon.impl.domain.interactor

import com.mama1emon.api.data.Repository
import com.mama1emon.api.domain.interactor.Interactor
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Реализация интерактора
 *
 * @param repository репозиторий приложения
 *
 * @author Andrey Khokhlov on 26.03.21
 */
class InteractorImpl(
    private val repository: Repository
) : Interactor {

    override fun getStockSetContent(): Single<Set<Stock>> {
        return repository.getStockSetContent()
    }

    override fun getStockQuote(ticker: String): Observable<StockQuote> {
        return repository.getStockQuote(ticker)
    }
}