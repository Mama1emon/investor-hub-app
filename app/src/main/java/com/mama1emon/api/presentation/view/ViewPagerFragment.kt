package com.mama1emon.api.presentation.view

import androidx.fragment.app.Fragment

/**
 * Определяет дополнительные методы жизненного цикла для работы с ViewPager
 *
 * @author Andrey Khokhlov on 18.04.21
 */
abstract class ViewPagerFragment : Fragment() {

    /**
     * Вызывается, когда фрагмент во ViewPager становится видимым
     */
    abstract fun onStartFragment()
}