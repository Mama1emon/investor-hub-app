package com.mama1emon.impl.presentation.viewholder

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.mama1emon.R
import com.mama1emon.impl.model.domain.Stock
import com.mama1emon.impl.util.Font
import com.mama1emon.impl.util.IconManager
import com.mama1emon.impl.util.setTypefaceByFont
import java.text.DecimalFormat

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

    private val root: CardView
    private val stockIcon: ImageView
    private val stockNameTextView: TextView
    private val companyNameTextView: TextView
    private val currentPriceTextView: TextView
    private val dayDeltaTextView: TextView
    private val favouriteStockCheckBox: CheckBox

    private val iconManager = IconManager()

    init {
        with(view) {
            root = findViewById(R.id.item_root_view)
            stockIcon = findViewById(R.id.stock_icon)
            stockNameTextView = findViewById(R.id.stock_name)
            companyNameTextView = findViewById(R.id.company_name)
            currentPriceTextView = findViewById(R.id.current_price)
            dayDeltaTextView = findViewById(R.id.day_delta)
            favouriteStockCheckBox = findViewById(R.id.favourite_stock_check_box)
        }
    }

    /**
     * Сеттит данные
     *
     * @param stock данные об акции
     */
    fun bind(stock: Stock, favouriteStockCheckBoxClickListener: (View, String) -> Unit) {
        setTypeface()
        stockIcon.setImageDrawable(
            ResourcesCompat.getDrawable(
                itemView.resources,
                iconManager.getIcon(stock.iconUrl),
                itemView.context.theme
            )
        )
        stockNameTextView.text = stock.ticker
        companyNameTextView.text = stock.name

        stock.isFavourite?.let {
            favouriteStockCheckBox.isChecked = it
        }

        stock.quote?.let { quote ->
            val dayDelta: Double = quote.currentPrice - quote.openPrice
            currentPriceTextView.text = "$${stock?.quote?.currentPrice}"
            dayDeltaTextView.text =
                "${dayDelta.roundToTwoDecimalPlaces()}$ (${(dayDelta / 100).roundToTwoDecimalPlaces()}%)"
            if (dayDelta < 0) {
                dayDeltaTextView.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        R.color.colorNegativeDayDelta
                    )
                )
            } else {
                dayDeltaTextView.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        R.color.colorPositiveDayDelta
                    )
                )
            }
        }

        favouriteStockCheckBox.setOnClickListener {
            favouriteStockCheckBoxClickListener.invoke(it, stock.ticker)
        }
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
     * Сеттит цвет итему
     */
    fun setBackgroundColor(@ColorRes color: Int) = with(root) {
        setCardBackgroundColor(ContextCompat.getColor(context, color))
    }

    private fun Double.roundToTwoDecimalPlaces() = DecimalFormat("####.##").format(this)
}