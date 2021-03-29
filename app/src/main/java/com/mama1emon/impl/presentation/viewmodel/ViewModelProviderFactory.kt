package com.mama1emon.impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.function.Supplier

/**
 * Фабрика для получения экземпляра вью модели
 *
 * @author Andrey Khokhlov on 28.03.21
 */
class ViewModelProviderFactory<VM : ViewModel>(
    private val viewModelCreator: Supplier<VM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelCreator.get() as T
    }
}