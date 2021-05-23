package com.mama1emon.impl.util

import android.graphics.Typeface
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

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

/**
 * Установить шрифт
 *
 * @param font шрифт
 */
fun TextView.setFont(font: Font) {
    this.typeface = Typeface.createFromAsset(this.context.assets, font.path)
}

/**
 * Используемые шрифты
 *
 * @param path путь до шрифта
 */
enum class Font(val path: String) {
    Montserrat600("font/montserrat600.ttf"),
    Montserrat700("font/montserrat700.ttf")
}