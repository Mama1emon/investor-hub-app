package com.mama1emon.impl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.impl.R
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.presentation.viewholder.StockListViewHolder

/**
 * Адаптер для листа акций
 *
 * @author Andrey Khokhlov on 24.03.21
 */
class StockListAdapter : RecyclerView.Adapter<StockListViewHolder>() {

    private lateinit var stockList: List<Stock>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock_item, parent, false)
        return StockListViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockListViewHolder, position: Int) {
        holder.bind(stockList[position])
        if (position % 2 == 0) {
            holder.setDarkBackground()
        }
    }

    override fun getItemCount(): Int = stockList.size

    /**
     * Сеттит данные в ресайклер
     *
     * @param list список акций
     */
    fun setData(list: List<Stock>) {
        stockList = list
        notifyDataSetChanged()
    }
}