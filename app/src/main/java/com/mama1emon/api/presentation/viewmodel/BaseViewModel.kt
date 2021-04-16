package com.mama1emon.api.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Базовый класс, от которого наследуются все [ViewModel]
 *
 * @author Andrey Khokhlov on 16.04.21
 */
abstract class BaseViewModel : ViewModel() {
    protected val rxCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        rxCompositeDisposable.clear()
    }
}