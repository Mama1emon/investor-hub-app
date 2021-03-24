package com.mama1emon.impl.presentation.router

import androidx.fragment.app.FragmentManager

/**
 * Роутер для навигации между экранами
 *
 * @author Andrey Khokhlov on 23.03.21
 */
interface Router {
    fun showStockList(fragmentManager: FragmentManager, addToBackStack: Boolean)
}