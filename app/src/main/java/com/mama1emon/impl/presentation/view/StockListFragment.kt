package com.mama1emon.impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.R
import com.mama1emon.api.App
import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.presentation.adapter.StockListAdapter
import com.mama1emon.impl.presentation.viewmodel.StockFragmentContentViewModel
import com.mama1emon.impl.presentation.viewmodel.ViewModelProviderFactory

/**
 * Фрагмент со списком акций
 *
 * @author Andrey Khokhlov on 23.03.21
 */
class StockListFragment : Fragment() {

    private lateinit var viewModel: StockFragmentContentViewModel
    private val interactor = App.instance?.getInteractor()
    private val database = App.instance?.getDatabase()

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

        if (interactor != null && database != null) {
            viewModel = ViewModelProvider(viewModelStore, ViewModelProviderFactory {
                StockFragmentContentViewModel(interactor, database)
            }).get(StockFragmentContentViewModel::class.java)
        }

        observeData()

        viewModel.cachedStockList?.let { setOfStock ->
            stockListAdapter.setData(setOfStock)

            setOfStock.forEach { stock ->
                viewModel.loadStockQuoteContent(stock.ticker)
            }
        } ?: viewModel.loadStockSetContent()
    }

    private fun findViews(view: View) = with(view) {
        stockRecyclerView = findViewById(R.id.stock_recycler_view)
    }

    private fun setupRecyclerView() {
        stockListAdapter = StockListAdapter { checkBox, ticker ->
            val favouriteStockCheckBox = checkBox as CheckBox
            if (favouriteStockCheckBox.isChecked) {
                viewModel.saveFavouriteStock(FavouriteStock(ticker = ticker))
            } else {
                viewModel.deleteFavouriteStock(ticker)
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
        viewModel.stockListContent.observe(viewLifecycleOwner, { setOfStock ->
            stockListAdapter.setData(setOfStock)

            viewModel.loadFavouriteStockSet()

            //для каждого тикета запрашиваем инфо о котировках
            setOfStock.forEach { stock ->
                viewModel.loadStockQuoteContent(stock.ticker)
            }
        })

        viewModel.stockQuoteContent.observe(viewLifecycleOwner, { quote ->
            stockListAdapter.setStockQuote(quote)
        })

        viewModel.favouriteStockList.observe(viewLifecycleOwner, { favouriteStocks ->
            favouriteStocks.forEach {
                stockListAdapter.setFavouriteStock(it)
            }
        })
    }
}