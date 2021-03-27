package com.mama1emon.impl.util

import androidx.annotation.DrawableRes
import com.mama1emon.impl.R

/**
 * Менеджер для работы с иконками
 *
 * @author Andrey Khokhlov on 27.03.21
 */
class IconManager(
    @DrawableRes private val defaultIconId: Int = R.drawable.ic_default_icon
) {
    private val iconMap: Map<String, Int> = initIcons()

    fun getIcon(iconUrl: String) = iconMap[iconUrl] ?: defaultIconId

    companion object {
        fun initIcons() = mutableMapOf(
            ("YNDX" to R.drawable.ic_yndx),
            ("TSLA" to R.drawable.ic_tsla),
            ("AAPL" to R.drawable.ic_aapl),
            ("AMZN" to R.drawable.ic_amzn),
            ("GOOGL" to R.drawable.ic_googl),
            ("MSFT" to R.drawable.ic_msft)
        )
    }

}