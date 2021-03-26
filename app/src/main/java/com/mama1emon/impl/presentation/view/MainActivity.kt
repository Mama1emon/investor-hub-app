package com.mama1emon.impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.mama1emon.impl.R
import com.mama1emon.impl.presentation.adapter.PrimaryMenuPagerAdapter
import com.mama1emon.impl.util.Font
import com.mama1emon.impl.util.setTypefaceByFont
import com.mama1emon.impl.util.setWeight
import com.mama1emon.impl.util.setWidth


/**
 * Главный activity
 *
 * @author Andrey Khokhlov
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var searchTextView: TextView
    private lateinit var fragmentPagerMap: MutableMap<String, Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        findViews()
        setupHeader()
        initFragmentPagerMap()

        setupViewPager()
        setupTabLayout()

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until tabLayout.tabCount) {
                    (tabLayout.getTabAt(i)?.customView as TextView).apply {
                        setTextAppearance(R.style.TextAppearance_Headline2)
                        setTypefaceByFont(Font.Montserrat700)
                    }
                }
                setStyleCurrentFragmentTitle()
            }
        })
    }

    private fun findViews() = with(this) {
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        searchTextView = findViewById(R.id.search_edit_view)
    }

    private fun initFragmentPagerMap() {
        fragmentPagerMap = mutableMapOf<String, Fragment>().apply {
            put(resources.getString(R.string.stocks), StockListFragment())
            put(resources.getString(R.string.favourites), StockListFragment())
            put("Chart", StockListFragment())
            put("Summary", StockListFragment())
            put("News", StockListFragment())
        }
    }

    private fun setupHeader() {
        searchTextView.setTypefaceByFont(Font.Montserrat600)
        searchTextView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                searchTextView.hint = ""
            } else {
                searchTextView.hint = resources.getString(R.string.find_company_or_ticker)
            }
        }
    }

    private fun setupViewPager() {
        viewPager.adapter = PrimaryMenuPagerAdapter(supportFragmentManager).apply {
            fragmentPagerMap.entries.forEach { pair ->
                addFragment(pair.key, pair.value)
            }
        }
    }

    private fun setupTabLayout() = with(tabLayout) {
        setupWithViewPager(viewPager)

        var counter = 0
        fragmentPagerMap.keys.forEach { fragmentTitle ->
            val view = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.custom_tab_view, this, false)

            // Изменить вес и ширину tab
            (getChildAt(0) as ViewGroup).getChildAt(counter).apply {
                setWeight(0f)
                setWidth(LinearLayout.LayoutParams.WRAP_CONTENT)
            }

            getTabAt(counter)?.customView = (view as TextView).apply {
                text = fragmentTitle
                setTypefaceByFont(Font.Montserrat700)
            }

            counter++
        }

        setStyleCurrentFragmentTitle()
    }

    private fun setStyleCurrentFragmentTitle() = with(tabLayout) {
        (getTabAt(selectedTabPosition)?.customView as TextView).apply {
            setTextAppearance(R.style.TextAppearance_Headline1)
            setTypefaceByFont(Font.Montserrat700)
        }
    }
}