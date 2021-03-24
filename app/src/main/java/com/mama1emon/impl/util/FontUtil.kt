package com.mama1emon.impl.util

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity

/**
 * Получить шрифт
 */
fun getTypefaceByFont(activity: AppCompatActivity, font: Font): Typeface =
    Typeface.createFromAsset(activity.assets, font.path)

/**
 * Используемые шрифты
 */
enum class Font(val path: String) {
    Montserrat600("font/montserrat600.ttf"),
    Montserrat700("font/montserrat700.ttf")
}
