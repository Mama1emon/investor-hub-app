package com.mama1emon.impl.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.R
import com.mama1emon.impl.model.data.entity.FavouriteStock
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.model.domain.StockQuote
import com.mama1emon.impl.presentation.viewholder.StockListViewHolder

/**
 * Адаптер для листа акций
 *
 * @author Andrey Khokhlov on 24.03.21
 */
class StockListAdapter(
    private val favouriteStockCheckBoxListener: (View, String) -> Unit
) : RecyclerView.Adapter<StockListViewHolder>() {

    private var stockList = listOf<Stock>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return StockListViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockListViewHolder, position: Int) {
        holder.bind(stockList[position], favouriteStockCheckBoxListener)

        if (position % 2 == 0) {
            holder.setBackgroundColor(R.color.colorDarkStockItem)
        } else {
            holder.setBackgroundColor(R.color.colorPrimary)
        }
    }

    override fun getItemCount(): Int = stockList.size

    /**
     * Сеттит список акций в ресайклер
     *
     * @param set множество акций
     */
    fun setData(set: Set<Stock>) {
        stockList = set.toMutableList()
        notifyDataSetChanged()
    }

    fun setStockQuote(quote: StockQuote) {
        val oldStock = stockList.find { it.ticker == quote.ticker }
        oldStock?.let {
            it.quote = quote
            notifyItemChanged(stockList.indexOf(oldStock))
        }
    }

    fun setFavouriteStock(stock: FavouriteStock) {
        val oldStock = stockList.find { it.ticker == stock.ticker }
        oldStock?.let {
            it.isFavourite = true
            notifyItemChanged(stockList.indexOf(oldStock))
        }
    }
}