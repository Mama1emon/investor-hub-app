package com.mama1emon.impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mama1emon.api.data.AppDatabase
import com.mama1emon.api.domain.interactor.Interactor
import com.mama1emon.api.presentation.viewmodel.BaseViewModel
import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import com.mama1emon.impl.util.MultipleLiveEvent
import com.mama1emon.impl.util.addTo
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
) : BaseViewModel() {

    var cachedStockSet: Set<Stock>? = null
    var cachedFavouriteStockSet = mutableSetOf<FavouriteStock>()
    var isChangeFavouriteStocks = false

    private val stockSetContentMutableLiveData = MutableLiveData<Set<Stock>>()
    val stockSetContent: LiveData<Set<Stock>> = stockSetContentMutableLiveData

    private val stockQuoteContentMutableLiveData = MultipleLiveEvent<StockQuote>()
    val stockQuoteContent: LiveData<StockQuote> = stockQuoteContentMutableLiveData

    private val favouriteStockSetMutableLiveData = MutableLiveData<Set<FavouriteStock>>()
    val favouriteStockSet: LiveData<Set<FavouriteStock>> = favouriteStockSetMutableLiveData

    /**
     * Загрузить набор акций
     */
    fun loadStockSetContent() {
        interactor.getStockSetContent()
            .subscribeOn(Schedulers.io())
            .subscribe({ stockSet ->
                stockSetContentMutableLiveData.postValue(stockSet)
                cachedStockSet = stockSet
            }, {
                Log.e(it.toString(), "getStockList() finished with an error")
            })
            .addTo(rxCompositeDisposable)
    }

    /**
     * Загрузить котировки акции по тикеру
     *
     * @param ticker акции
     */
    fun loadStockQuoteContent(ticker: String) {
        interactor.getStockQuote(ticker)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ quote ->
                stockQuoteContentMutableLiveData.value = quote
            }, {
                Log.e(it.toString(), "getStockQuote() finished with an error")
            })
            .addTo(rxCompositeDisposable)
    }

    /**
     * Загрузить список любимых акций
     */
    fun loadFavouriteStockSet() {
        interactor.getFavouriteStockList()
            .subscribeOn(Schedulers.io())
            .subscribe({ favouriteStockList ->
                favouriteStockSetMutableLiveData.postValue(favouriteStockList.toSet())
                cachedFavouriteStockSet = favouriteStockList.toMutableSet()
            }, {
                Log.e(it.toString(), "getFavouriteStock() finished with an error")
            })
            .addTo(rxCompositeDisposable)
    }

    /**
     * Сохранить любимую акции
     *
     * @param stock любимая акция
     */
    fun saveFavouriteStock(stock: FavouriteStock) {
        isChangeFavouriteStocks = true
        interactor.saveFavouriteStocks(stock)
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(rxCompositeDisposable)
    }

    /**
     * Удалить любимую акцию
     *
     * @param ticker акции
     */
    fun deleteFavouriteStock(ticker: String) {
        isChangeFavouriteStocks = true
        interactor.deleteFavouriteStock(ticker)
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(rxCompositeDisposable)
    }
}