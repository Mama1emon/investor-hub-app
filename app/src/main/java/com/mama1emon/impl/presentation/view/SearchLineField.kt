package com.mama1emon.impl.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.mama1emon.R
import com.mama1emon.impl.util.Font
import com.mama1emon.impl.util.setFont

/**
 * Кастомная вью, отображающая строку поиска
 *
 * @param context контекст
 * @param attrs набор атрибутов
 *
 * @author Andrey Khokhlov on 03.05.21
 */
class SearchLineField(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val root: View = View.inflate(context, R.layout.search_line_field, this)
    private val leftCompoundIcon: AppCompatImageView = root.findViewById(R.id.left_icon)
    private val rightCompoundIcon: AppCompatImageView = root.findViewById(R.id.right_icon)
    private val searchEditView: AppCompatEditText = root.findViewById(R.id.enter_text_view)

    init {
        setHint(resources.getString(R.string.find_company_or_ticker))
        setHintFont(Font.Montserrat600)
        setCompoundDrawable(leftId = R.drawable.ic_search_24)
        searchEditView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                setHint("")
                setCompoundDrawable(
                    leftId = R.drawable.ic_back_24,
                    rightId = R.drawable.ic_exit_24
                )
                leftCompoundIcon.setOnClickListener {
                    searchEditView.text?.clear()
                    searchEditView.clearFocus()
                }
                rightCompoundIcon.setOnClickListener {
                    searchEditView.text?.clear()
                }
            } else {
                setHint(resources.getString(R.string.find_company_or_ticker))
                setCompoundDrawable(leftId = R.drawable.ic_search_24)
                rightCompoundIcon.visibility = View.GONE
            }
        }
        searchEditView.addTextChangedListener {
            if (it.isNullOrBlank()) {
                rightCompoundIcon.visibility = View.INVISIBLE
            }
        }
    }

    /**
     * Установить хинт строки поиска
     *
     * @param hint текст
     */
    fun setHint(hint: String) {
        searchEditView.hint = hint
    }

    /**
     * Установить шрифт хинта строки поиска
     *
     * @param font шрифт
     */
    fun setHintFont(font: Font) {
        searchEditView.setFont(font)
    }

    /**
     * Установить изображения для строки поиска слева и/или справа от [AppCompatEditText]
     *
     * @param leftId ссылка на ресурс изображения слева от [AppCompatEditText]
     * @param rightId ссылка на ресурс изображения справа от [AppCompatEditText]
     */
    fun setCompoundDrawable(@DrawableRes leftId: Int = 0, @DrawableRes rightId: Int = 0) {
        if (leftId != 0) {
            leftCompoundIcon.visibility = View.VISIBLE
            leftCompoundIcon.setImageResource(leftId)
        }
        if (rightId != 0) {
            rightCompoundIcon.visibility = View.VISIBLE
            rightCompoundIcon.setImageResource(rightId)
        }
    }

    /**
     * Установить листенер нажатий
     *
     * @param clickListener действие
     */
    fun setClickListener(clickListener: () -> Unit) {
        root.setOnClickListener {
            clickListener.invoke()
        }
    }
}