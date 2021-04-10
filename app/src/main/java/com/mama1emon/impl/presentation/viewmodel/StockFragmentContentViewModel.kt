package com.mama1emon.impl.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mama1emon.api.data.AppDatabase
import com.mama1emon.api.domain.interactor.Interactor
import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import com.mama1emon.impl.util.MultipleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Вью модел для фрагмента со списком акций
 *
 * @param interactor приложения
 * @param database приложения
 *
 * @author Andrey Khokhlov on 27.03.21
 */
class StockFragmentContentViewModel(
    private val interactor: Interactor,
    private val database: AppDatabase
) : ViewModel() {

    var cachedStockList: MutableSet<Stock>? = null
    var cachedFavouriteStockSet = mutableSetOf<FavouriteStock>()

    private val stockListContentMutableLiveData = MutableLiveData<Set<Stock>>()
    val stockListContent: LiveData<Set<Stock>> = stockListContentMutableLiveData

    private val stockQuoteContentMutableLiveData = MultipleLiveEvent<StockQuote>()
    val stockQuoteContent: LiveData<StockQuote> = stockQuoteContentMutableLiveData

    private val favouriteStockListMutableLiveData = MutableLiveData<Set<FavouriteStock>>()
    val favouriteStockList: LiveData<Set<FavouriteStock>> = favouriteStockListMutableLiveData

    /**
     * Загрузить набор акций
     */
    @SuppressLint("CheckResult")
    fun loadStockSetContent() {
        interactor.getStockSetContent()
            .subscribeOn(Schedulers.io())
            .subscribe({
                stockListContentMutableLiveData.postValue(it)
                cachedStockList = it.toMutableSet()
            }, {
                Log.e(it.toString(), "getStockList() finished with an error")
            })
    }

    /**
     * Загрузить котировки акции по тикеру
     *
     * @param ticker акции
     */
    @SuppressLint("CheckResult")
    fun loadStockQuoteContent(ticker: String) {
        interactor.getStockQuote(ticker)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ quote ->
                cachedStockList?.let { stockList ->
                    val oldStock = stockList.first { it.ticker == ticker }
                    oldStock.quote = quote
                }
                stockQuoteContentMutableLiveData.value = quote
            }, {
                Log.e(it.toString(), "getStockQuote() finished with an error")
            })
    }

    /**
     * Загрузить список любимых акций
     */
    @SuppressLint("CheckResult")
    fun loadFavouriteStockSet() {
        interactor.getFavouriteStockList()
            .subscribeOn(Schedulers.io())
            .subscribe({ favouriteStockList ->
                favouriteStockListMutableLiveData.postValue(favouriteStockList.toSet())
                cachedFavouriteStockSet = favouriteStockList.toMutableSet()
            }, {
                Log.e(it.toString(), "getFavouriteStock() finished with an error")
            })
    }

    /**
     * Сохранить любимую акции
     *
     * @param stock любимая акция
     */
    fun saveFavouriteStock(stock: FavouriteStock) {
        interactor.saveFavouriteStocks(stock)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    /**
     * Удалить любимую акцию
     *
     * @param ticker акции
     */
    fun deleteFavouriteStock(ticker: String) {
        interactor.deleteFavouriteStock(ticker)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}