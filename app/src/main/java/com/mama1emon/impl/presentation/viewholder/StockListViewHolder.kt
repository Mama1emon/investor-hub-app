package com.mama1emon.impl.presentation.viewholder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.impl.R
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.util.Font
import com.mama1emon.impl.util.setTypefaceByFont

/**
 * Вью холдер с описанием акции
 *
 * @param view вью, которую инфлейтит
 *
 * @author Andrey Khokhlov on 24.03.21
 */
class StockListViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val stockIcon: CardView
    private val stockNameTextView: TextView
    private val companyNameTextView: TextView
    private val currentPriceTextView: TextView
    private val dayDeltaTextView: TextView

    init {
        with(view) {
            stockIcon = findViewById(R.id.stock_icon)
            stockNameTextView = findViewById(R.id.stock_name)
            companyNameTextView = findViewById(R.id.company_name)
            currentPriceTextView = findViewById(R.id.current_price)
            dayDeltaTextView = findViewById(R.id.day_delta)
        }
    }

    /**
     * Сеттит данные
     *
     * @param stock данные об акции
     */
    fun bind(stock: Stock) {
        setTypeface()
        //stockIcon
        stockNameTextView.text = stock.stockName
        companyNameTextView.text = stock.companyName
        currentPriceTextView.text = stock.currentPrice
        dayDeltaTextView.text = stock.dayDelta
    }

    /**
     * Изменяет шрифт вью
     */
    private fun setTypeface() {
        stockNameTextView.setTypefaceByFont(Font.Montserrat700)
        companyNameTextView.setTypefaceByFont(Font.Montserrat600)
        currentPriceTextView.setTypefaceByFont(Font.Montserrat700)
        dayDeltaTextView.setTypefaceByFont(Font.Montserrat600)
    }

    /**
     * Сеттит темный бэкграунд
     */
    fun setDarkBackground() = with(view as CardView) {
        setCardBackgroundColor(resources.getColor(R.color.colorStockListItem, context.theme))
    }
}