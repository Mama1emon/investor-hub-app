package com.mama1emon.impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.tabs.TabLayout
import com.mama1emon.R
import com.mama1emon.api.App
import com.mama1emon.api.presentation.view.ViewPagerFragment
import com.mama1emon.impl.presentation.adapter.PrimaryMenuPagerAdapter
import com.mama1emon.impl.presentation.viewmodel.StockFragmentContentViewModel
import com.mama1emon.impl.presentation.viewmodel.ViewModelProviderFactory
import com.mama1emon.impl.util.*
import kotlin.math.abs

/**
 * Главный activity
 *
 * @author Andrey Khokhlov
 */
class MainActivity : AppCompatActivity() {
    private val interactor = App.instance?.getInteractor()
    private val sharedPreferences = App.instance?.getSharedPreferences()
    private lateinit var viewModel: StockFragmentContentViewModel

    private lateinit var appBarLayout: AppBarLayout
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var searchLineField: SearchLineField

    private lateinit var viewPagerAdapter: PrimaryMenuPagerAdapter
    private lateinit var fragmentPagerMap: MutableMap<String, ViewPagerFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (interactor != null && sharedPreferences != null) {
            viewModel = ViewModelProvider(viewModelStore, ViewModelProviderFactory {
                StockFragmentContentViewModel(interactor, sharedPreferences)
            }).get(StockFragmentContentViewModel::class.java)
        }

        findViews()
        initFragmentPagerMap()

        setupViewPager()
        setupTabLayout()

        appBarLayout.addOnOffsetChangedListener(
            OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (verticalOffset == 0){
                    appBarLayout.elevation = 0f
                } else if (abs(verticalOffset) < appBarLayout.totalScrollRange) {
                    appBarLayout.elevation = 5f
                }
            }
        )

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                (viewPagerAdapter.getItem(position) as ViewPagerFragment).onStartFragment()

                for (i in 0 until tabLayout.tabCount) {
                    (tabLayout.getTabAt(i)?.customView as TextView).apply {
                        setTextAppearance(R.style.TextAppearance_Headline2)
                        setFont(Font.Montserrat700)
                    }
                }
                setStyleCurrentFragmentTitle()
            }
        })

        viewModel.cachedStockSet ?: viewModel.loadStockSetContent()

        viewModel.stockSetContent.observe(this, { stockSet ->
            viewModel.loadFavouriteStockSet()

            stockSet.forEach { stock ->
                viewModel.loadStockQuoteContent(stock.ticker)
            }
        })
    }

    private fun findViews() {
        appBarLayout = findViewById(R.id.app_bar)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        searchLineField = findViewById(R.id.search_view)
    }

    private fun initFragmentPagerMap() {
        fragmentPagerMap = mutableMapOf<String, ViewPagerFragment>().apply {
            put(resources.getString(R.string.stocks), StockListFragment())
            put(resources.getString(R.string.favourites), FavouriteStockListFragment())
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = PrimaryMenuPagerAdapter(supportFragmentManager).apply {
            fragmentPagerMap.entries.forEach { pair ->
                addFragment(pair.key, pair.value)
            }
        }
        viewPager.adapter = viewPagerAdapter
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
                setFont(Font.Montserrat700)
            }

            counter++
        }

        setStyleCurrentFragmentTitle()
    }

    private fun setStyleCurrentFragmentTitle() = with(tabLayout) {
        (getTabAt(selectedTabPosition)?.customView as TextView).apply {
            setTextAppearance(R.style.TextAppearance_Headline1)
            setFont(Font.Montserrat700)
        }
    }
}