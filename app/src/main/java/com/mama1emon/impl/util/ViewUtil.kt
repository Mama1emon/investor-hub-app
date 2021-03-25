package com.mama1emon.impl.util

import android.view.View
import android.widget.LinearLayout

/**
 * Установить вес вью
 *
 * @param weight вес вью
 */
fun View.setWeight(weight: Float) {
    (layoutParams as LinearLayout.LayoutParams).weight = weight
}

/**
 * Установить ширину вью
 *
 * @param width ширина вью
 */
fun View.setWidth(width: Int) {
    (layoutParams as LinearLayout.LayoutParams).width = width
}