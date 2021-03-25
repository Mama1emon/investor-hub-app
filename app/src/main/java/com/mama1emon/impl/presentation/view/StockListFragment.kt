package com.mama1emon.impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.impl.R
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.presentation.adapter.StockListAdapter

/**
 * Фрагмент со списком акций
 *
 * @author Andrey Khokhlov on 23.03.21
 */
class StockListFragment : Fragment() {

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

        stockRecyclerView.addItemDecoration(
            EmptySpaceItemDecoration(
                verticalSpace = 8
            )
        )
        stockRecyclerView.adapter = StockListAdapter().apply {
            setData(
                listOf(
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    ),
                    Stock(
                        iconUrl = "",
                        stockName = "YNDX",
                        companyName = "Yandex, LLC",
                        currentPrice = "4 764,6 р",
                        dayDelta = "+0.12"
                    )
                )
            )
        }
    }

    private fun findViews(view: View) = with(view) {
        stockRecyclerView = findViewById(R.id.stock_recycler_view)
    }

    companion object {
        fun getInstance() = StockListFragment()
    }
}