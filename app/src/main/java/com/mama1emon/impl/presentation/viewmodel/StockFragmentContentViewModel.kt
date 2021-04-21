package com.mama1emon.impl.presentation.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mama1emon.api.domain.interactor.Interactor
import com.mama1emon.api.presentation.viewmodel.BaseViewModel
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import com.mama1emon.impl.util.addTo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Вью модел для фрагмента со списком акций
 *
 * @param interactor приложения
 * @param sharedPreferences приложения
 *
 * @author Andrey Khokhlov on 27.03.21
 */
class StockFragmentContentViewModel(
    private val interactor: Interactor,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    var cachedStockSet: Set<Stock>? = null
    var cachedFavouriteStockSet = mutableSetOf<Stock>()
    var isChangeFavouriteStocks = false
    private var cachedQuoteSet = mutableSetOf<StockQuote>()

    private val stockSetContentMutableLiveData = MutableLiveData<Set<Stock>>()
    val stockSetContent: LiveData<Set<Stock>> = stockSetContentMutableLiveData

    private val stockQuoteContentMutableLiveData = MutableLiveData<StockQuote>()
    val stockQuoteContent: LiveData<StockQuote> = stockQuoteContentMutableLiveData

    private val favouriteStockSetMutableLiveData = MutableLiveData<Set<Stock>>()
    val favouriteStockSet: LiveData<Set<Stock>> = favouriteStockSetMutableLiveData

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
            .subscribe({ newQuote ->
                // проверка: изменились ли котировки
                val foundQuote = cachedQuoteSet.find { it.ticker == newQuote.ticker }
                if (foundQuote == null) {
                    stockQuoteContentMutableLiveData.value = newQuote
                    cachedQuoteSet.add(newQuote)
                } else if (newQuote != foundQuote) {
                    stockQuoteContentMutableLiveData.value = newQuote
                    cachedQuoteSet.removeIf { it.ticker == newQuote.ticker }
                    cachedQuoteSet.add(newQuote)
                }
            }, {
                Log.e(it.toString(), "getStockQuote() finished with an error")
            })
            .addTo(rxCompositeDisposable)
    }

    /**
     * Загрузить список любимых акций
     */
    fun loadFavouriteStockSet() {
        Single.fromCallable {
            val favouriteStockSet = mutableSetOf<Stock>()
            cachedStockSet?.forEach { stock ->
                if (sharedPreferences.getBoolean(stock.ticker, false)) {
                    favouriteStockSet.add(stock)
                }
            }
            return@fromCallable favouriteStockSet
        }
            .subscribeOn(Schedulers.io())
            .subscribe({ favouriteStockSet ->
                favouriteStockSetMutableLiveData.postValue(favouriteStockSet)
                cachedFavouriteStockSet = favouriteStockSet
            }, {
                Log.e(it.toString(), "getFavouriteStock() finished with an error")
            })
            .addTo(rxCompositeDisposable)
    }

    /**
     * Сохранить любимую акции
     *
     * @param savedStock акции
     */
    fun saveFavouriteStock(savedStock: Stock) {
        sharedPreferences.edit().putBoolean(savedStock.ticker, true).apply()
        cachedFavouriteStockSet.add(savedStock.apply { isFavourite = true })
        isChangeFavouriteStocks = true
    }

    /**
     * Удалить любимую акцию
     *
     * @param removedTicker акции
     */
    fun deleteFavouriteStock(removedTicker: String) {
        cachedFavouriteStockSet.removeIf { it.ticker == removedTicker }
        sharedPreferences.edit().remove(removedTicker).apply()
        isChangeFavouriteStocks = true
    }
}