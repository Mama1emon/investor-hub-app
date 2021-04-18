package com.mama1emon.impl.presentation.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.R
import com.mama1emon.api.presentation.view.ViewPagerFragment
import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.presentation.adapter.StockListAdapter
import com.mama1emon.impl.presentation.viewmodel.StockFragmentContentViewModel

/**
 * Фрагмент со списком любимых акций
 *
 * @author Andrey Khokhlov on 23.03.21
 */
class FavouriteStockListFragment : ViewPagerFragment() {
    private val viewModel: StockFragmentContentViewModel by activityViewModels()

    private lateinit var stockRecyclerView: RecyclerView
    private lateinit var stockListAdapter: StockListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.stock_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews(view)
        setupRecyclerView()

        observeData()
    }

    private fun findViews(view: View) = with(view) {
        stockRecyclerView = findViewById(R.id.stock_recycler_view)
    }

    override fun onStartFragment() {
        // если изменился список любимых акций
        if (viewModel.isChangeFavouriteStocks) {
            // кешируем с задержкой, чтобы не перегружать UI
            Handler(Looper.getMainLooper()).postDelayed({
                val favouriteTickerSet = viewModel.cachedFavouriteStockSet.map { it.ticker }
                viewModel.cachedStockSet?.let { stockSet ->
                    val resultStockSet = stockSet.filter { stock ->
                        stock.ticker in favouriteTickerSet
                    }.toSet()

                    stockListAdapter.setData(resultStockSet)

                    resultStockSet.forEach { stock ->
                        viewModel.loadStockQuoteContent(stock.ticker)
                    }

                    stockListAdapter.setFavouriteStock(viewModel.cachedFavouriteStockSet)
                }
            }, CACHING_DELAY)

            viewModel.isChangeFavouriteStocks = false
        }
    }

    private fun setupRecyclerView() {
        stockListAdapter = StockListAdapter { checkBox, selectedTicker ->
            val favouriteStockCheckBox = checkBox as CheckBox
            val favouriteStock = FavouriteStock(ticker = selectedTicker)

            if (favouriteStockCheckBox.isChecked) {
                viewModel.saveFavouriteStock(favouriteStock)
                viewModel.cachedFavouriteStockSet.add(favouriteStock)
            } else {
                viewModel.deleteFavouriteStock(selectedTicker)
                stockListAdapter.removeFavoriteStock(FavouriteStock(ticker = selectedTicker))
                viewModel.cachedFavouriteStockSet.removeIf { cachedStock ->
                    cachedStock.ticker == selectedTicker
                }
            }
        }

        stockRecyclerView.adapter = stockListAdapter
        stockRecyclerView.addItemDecoration(
            EmptySpaceItemDecoration(
                verticalSpace = 8
            )
        )
    }

    private fun observeData() {
        viewModel.stockSetContent.observe(viewLifecycleOwner, { stockSet ->
            viewModel.favouriteStockSet.observe(viewLifecycleOwner, { favouriteStocks ->
                val favouriteTickerSet = favouriteStocks.map { it.ticker }
                val resultStockSet = stockSet.filter { stock ->
                    stock.ticker in favouriteTickerSet
                }.toSet()

                stockListAdapter.setData(resultStockSet)

                resultStockSet.forEach { stock ->
                    viewModel.loadStockQuoteContent(stock.ticker)
                }

                stockListAdapter.setFavouriteStock(favouriteStocks)
            })
        })
    }

    companion object {
        const val CACHING_DELAY = 200L
    }
}