package com.mama1emon.impl.presentation.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Пустое пространство между элементами ресайклера
 *
 * @param verticalSpace вертикальное пространство
 * @param horizontalSpace горизонтальное пространство
 *
 * @author Andrey Khokhlov on 24.03.21
 */
class EmptySpaceItemDecoration(
    private val verticalSpace: Int = 0,
    private val horizontalSpace: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpace
        outRect.left = horizontalSpace / 2
        outRect.right = horizontalSpace / 2
    }
}