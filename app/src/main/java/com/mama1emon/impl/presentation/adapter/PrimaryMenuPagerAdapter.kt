package com.mama1emon.impl.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Адаптер для горизонательного скролла между фрагментами
 *
 * @param fragmentManager фрагмент менеджер
 *
 * @author Andrey Khokhlov on 25.03.21
 */

class PrimaryMenuPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentTitleList = mutableListOf<String>()
    private val fragmentList = mutableListOf<Fragment>()

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getPageTitle(position: Int): CharSequence = fragmentTitleList[position]

    fun addFragment(fragmentTitle: String, fragment: Fragment) {
        fragmentTitleList.add(fragmentTitle)
        fragmentList.add(fragment)
    }
}