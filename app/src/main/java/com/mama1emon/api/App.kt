package com.mama1emon.api

import android.app.Application
import android.content.SharedPreferences
import com.mama1emon.api.data.Repository
import com.mama1emon.api.data.net.ApiMapper
import com.mama1emon.api.domain.interactor.Interactor
import com.mama1emon.impl.data.RepositoryImpl
import com.mama1emon.impl.data.net.ApiMapperImpl
import com.mama1emon.impl.domain.interactor.InteractorImpl

/**
 * Приложение
 * Лайт реализация DI
 *
 * @author Andrey Khokhlov on 30.03.21
 */
class App : Application() {
    private var sharedPreferences: SharedPreferences? = null
    private var apiMapper: ApiMapper? = null
    private var repository: Repository? = null
    private var interactor: Interactor? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferences = applicationContext.getSharedPreferences("InvestHubSP", MODE_PRIVATE)
        apiMapper = ApiMapperImpl(this.resources)
        repository = RepositoryImpl(apiMapper as ApiMapperImpl)
        interactor = InteractorImpl(repository as RepositoryImpl)
    }

    /**
     * Получить [SharedPreferences]
     */
    fun getSharedPreferences(): SharedPreferences? = sharedPreferences

    /**
     * Получить [ApiMapper]
     */
    fun getApiMapper(): ApiMapper? = apiMapper

    /**
     * Получить [Repository]
     */
    fun getRepository(): Repository? = repository

    /**
     * Получить [Interactor]
     */
    fun getInteractor(): Interactor? = interactor

    companion object {
        var instance: App? = null
    }
}