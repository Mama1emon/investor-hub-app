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
import com.mama1emon.impl.presentation.adapter.StockListAdapter
import com.mama1emon.impl.presentation.viewmodel.StockFragmentContentViewModel

/**
 * Фрагмент со списком акций
 *
 * @author Andrey Khokhlov on 23.03.21
 */
class StockListFragment : ViewPagerFragment() {
    private val viewModel: StockFragmentContentViewModel by activityViewModels()

    private lateinit var stockRecyclerView: RecyclerView
    private lateinit var stockListAdapter: StockListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_stock_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews(view)
        setupRecyclerView()

        observeData()
    }

    override fun onStartFragment() {
        // если изменились любимые акции
        if (viewModel.isChangeFavouriteStocks) {
            // кешируем с задержкой, чтобы не перегружать UI
            Handler(Looper.getMainLooper()).postDelayed({
                stockListAdapter.setFavouriteStockSet(viewModel.cachedFavouriteStockSet)
            }, CACHING_DELAY)

            viewModel.isChangeFavouriteStocks = false
        }
    }

    private fun findViews(view: View) = with(view) {
        stockRecyclerView = findViewById(R.id.stock_recycler_view)
    }

    private fun setupRecyclerView() {
        stockListAdapter = StockListAdapter { checkBox, selectedStock ->
            val favouriteStockCheckBox = checkBox as CheckBox

            if (favouriteStockCheckBox.isChecked) {
                viewModel.saveFavouriteStock(selectedStock)
            } else {
                viewModel.deleteFavouriteStock(selectedStock.ticker)
                stockListAdapter.changeStockStatusToUnfavourite(selectedStock.ticker)
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
            stockListAdapter.setData(stockSet)
        })

        viewModel.stockQuoteContent.observe(viewLifecycleOwner, { quote ->
            stockListAdapter.setStockQuote(quote)
        })

        viewModel.favouriteStockSet.observe(viewLifecycleOwner, { favouriteStocks ->
            stockListAdapter.setFavouriteStockSet(favouriteStocks)
        })
    }

    companion object {
        const val CACHING_DELAY = 200L
    }
}