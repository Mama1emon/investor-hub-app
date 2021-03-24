package com.mama1emon.impl.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mama1emon.api.presentation.router.RouterImpl
import com.mama1emon.impl.R


class MainActivity : AppCompatActivity() {

    private val router = RouterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        router.showStockList(
            fragmentManager = supportFragmentManager,
            addToBackStack = false
        )
    }
}