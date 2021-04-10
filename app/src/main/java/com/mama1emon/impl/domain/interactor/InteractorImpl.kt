package com.mama1emon.impl.domain.interactor

import com.mama1emon.api.data.AppDatabase
import com.mama1emon.api.data.Repository
import com.mama1emon.api.domain.interactor.Interactor
import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Реализация интерактора
 *
 * @param repository репозиторий приложения
 *
 * @author Andrey Khokhlov on 26.03.21
 */
class InteractorImpl(
    private val repository: Repository,
    private val database: AppDatabase
) : Interactor {

    override fun getStockSetContent(): Single<Set<Stock>> {
        return repository.getStockSetContent()
    }

    override fun getStockQuote(ticker: String): Single<StockQuote> {
        return repository.getStockQuote(ticker)
    }

    override fun getFavouriteStockList(): Single<List<FavouriteStock>> {
        return database.favouriteStockDao().getAll()
    }

    override fun saveFavouriteStocks(vararg stocks: FavouriteStock): Completable {
        return database.favouriteStockDao().insertAll(*stocks)
    }

    override fun deleteFavouriteStock(ticker: String): Completable {
        return database.favouriteStockDao().deleteByTicker(ticker)
    }
}