package com.mama1emon.api

import android.app.Application
import androidx.room.Room
import com.mama1emon.api.data.AppDatabase
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
    private var database: AppDatabase? = null
    private var apiMapper: ApiMapper? = null
    private var repository: Repository? = null
    private var interactor: Interactor? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "InvestHubDB")
            .build()
        apiMapper = ApiMapperImpl(this.resources)
        repository = RepositoryImpl(apiMapper as ApiMapperImpl)
        interactor = InteractorImpl(repository as RepositoryImpl, database!!)
    }

    fun getDatabase(): AppDatabase? = database

    fun getApiMapper(): ApiMapper? = apiMapper

    fun getRepository(): Repository? = repository

    fun getInteractor(): Interactor? = interactor

    companion object {
        var instance: App? = null
    }
}