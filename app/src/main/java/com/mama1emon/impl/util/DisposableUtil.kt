package com.mama1emon.impl.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Добавить [Disposable] в контейнер [CompositeDisposable] вью модели
 *
 * @param compositeDisposable контейнер диспосблов
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable =
    apply { compositeDisposable.add(this) }