package com.mama1emon.impl.presentation.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mama1emon.impl.R

/**
 * Фрагмент со списком акций
 *
 * @author Andrey Khokhlov on 23.03.21
 */
class StockListFragment : Fragment() {

    private lateinit var searchTextView: TextView
    private lateinit var stocksTitleView: TextView
    private lateinit var favouritesTitleView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.stock_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews(view)
        applyViewContent()
    }

    private fun findViews(view: View) = with(view) {
        searchTextView = findViewById(R.id.search_edit_view)
        stocksTitleView = findViewById(R.id.header_title_stocks)
        favouritesTitleView = findViewById(R.id.header_title_favourites)
    }

    private fun applyViewContent() {
        val activity = activity as AppCompatActivity

        val montserratTypeface = Typeface.createFromAsset(activity.assets, "font/montserrat700.ttf")
        stocksTitleView.typeface = montserratTypeface
        favouritesTitleView.typeface = montserratTypeface
    }

    companion object {
        fun getInstance() = StockListFragment()
    }
}