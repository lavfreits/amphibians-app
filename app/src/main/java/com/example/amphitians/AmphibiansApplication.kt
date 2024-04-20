package com.example.amphitians

import android.app.Application
import com.example.amphitians.data.AppContainer
import com.example.amphitians.data.DefaultAppContainer

class AmphibiansApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}