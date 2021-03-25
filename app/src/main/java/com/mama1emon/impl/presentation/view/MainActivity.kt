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
import com.mama1emon.impl.util.getTypefaceByFont
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
        applyViewTypeface()
        initFragmentPagerMap()

        setupViewPager()
        setupTabLayout()

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

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
        }
    }

    private fun applyViewTypeface() {
        searchTextView.typeface = getTypefaceByFont(this, Font.Montserrat600)
    }

    private fun setupViewPager() {
        viewPager.adapter = PrimaryMenuPagerAdapter(supportFragmentManager).apply {
            fragmentPagerMap.entries.forEach { pair ->
                addFragment(pair.key, pair.value)
            }
        }
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
        var counter = 0

        fragmentPagerMap.keys.forEach { fragmentTitle ->
            val view = LayoutInflater.from(this).inflate(R.layout.custom_tab_view, tabLayout, false)

            // Изменить вес и ширину tab
            (tabLayout.getChildAt(0) as ViewGroup).getChildAt(counter).apply {
                setWeight(0f)
                setWidth(LinearLayout.LayoutParams.WRAP_CONTENT)
            }

            tabLayout.getTabAt(counter)?.customView = (view as TextView).apply {
                text = fragmentTitle
                typeface = getTypefaceByFont(this@MainActivity, Font.Montserrat700)
            }

            counter++
        }
    }
}